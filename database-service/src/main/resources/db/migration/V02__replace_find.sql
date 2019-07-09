drop function public.find(data_like text, offs bigint, size integer);
create function find(data_like text, offs bigint, size int) returns table(id bigint, data text) as
$$
BEGIN
    PERFORM pg_sleep(3);
    return query select td.id, td.data from test_data td where td.data like '%' || data_like || '%' limit size offset offs;
end;
$$ language plpgsql;