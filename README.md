# Actividad 3.7 - Uso de JFreeChart en NetBeans

Se sigue el procedimiento de las diapositivas del profesor: Conexion,
GraficaXY, JFrame FConsultas, panel PGrafica, tabla TQuery, listas datos y
series. Cada boton declara su consulta, ejecuta cnx.consultar, crea la
grafica, la agrega al panel y llama a cnx.entablar.

Inicia MySQL en XAMPP. Abre el proyecto en NetBeans y ejecuta FConsultas.
Conexion en el constructor: localhost, base insulaina_1, root, clave vacia.
Paciente: ausl120130a. Consultas: promedio mensual, minimo/maximo y conteo.
Se usa nvl_glucosa, el dato trabajado en clase. La columna apli_insulina
del archivo del profesor no contiene valores.

Formulario editable en Design: FConsultas.form.
SQL para entregar: sql/consultas.sql. Capturas: evidencias/.
Repositorio: https://github.com/Orlandillo31/FSI_FreeChart_OJMG

