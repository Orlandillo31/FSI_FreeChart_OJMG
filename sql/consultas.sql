USE insulaina_1;

-- Promedio mensual de glucosa
SELECT DATE_FORMAT(fecha, '%Y-%m') AS periodo, ROUND(AVG(nvl_glucosa), 2) AS promedio
FROM mediciones
WHERE pacientes_curp = 'ausl120130a'
GROUP BY DATE_FORMAT(fecha, '%Y-%m')
ORDER BY periodo;

-- Minimo y maximo mensual de glucosa
SELECT DATE_FORMAT(fecha, '%Y-%m') AS periodo, MIN(nvl_glucosa) AS minimo, MAX(nvl_glucosa) AS maximo
FROM mediciones
WHERE pacientes_curp = 'ausl120130a'
GROUP BY DATE_FORMAT(fecha, '%Y-%m')
ORDER BY periodo;

-- Cantidad de mediciones por mes
SELECT DATE_FORMAT(fecha, '%Y-%m') AS periodo, COUNT(*) AS cantidad
FROM mediciones
WHERE pacientes_curp = 'ausl120130a'
GROUP BY DATE_FORMAT(fecha, '%Y-%m')
ORDER BY periodo;

