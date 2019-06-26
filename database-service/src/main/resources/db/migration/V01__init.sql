create table test_data (
    id bigserial primary key,
    data text not null
);
CREATE OR REPLACE FUNCTION random_str()
    RETURNS text AS
$$
declare
    vals text[] := ARRAY['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'] ::text[];
    val text := null;
BEGIN
    val := vals[floor(random() * 15)::int];

    if val is null then
        val := random_str();
    end if;

    return val;
END;
$$ language plpgsql;

create or replace function init_db(cnt bigint) returns void as
$$
declare
    rstr text := '';
begin
    for idx in 1 .. cnt loop
        rstr := random_str();
        insert into test_data (data) values (rstr);
    end loop;
end;
$$ language plpgsql;

create or replace function find(data_like text, offs bigint, size int) returns refcursor as
$$
DECLARE
    xxx refcursor;
BEGIN
    PERFORM pg_sleep(3);
    open xxx for select td.id, td.data from test_data td where td.data like '%' || data_like || '%' limit size offset offs;
    return xxx;
end;
$$ language plpgsql;

select init_db(1000000);