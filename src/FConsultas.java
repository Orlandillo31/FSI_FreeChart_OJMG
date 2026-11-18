import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FConsultas extends javax.swing.JFrame {

    // Paso 9: el primer campo de datos contiene los valores del eje X.
    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    ArrayList<String> series = new ArrayList<>();
    GraficaXY chart;
    Conexion cnx;

    public FConsultas() {
        initComponents();
        setLocationRelativeTo(null);
        // Pasos 6 y 7: crear el objeto y abrir la conexion.
        cnx = new Conexion();
        if (cnx.conectar("localhost", "insulaina_1", "root", "") == 0) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar a la BD");
            System.exit(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PEncabezado = new javax.swing.JPanel();
        LTitulo = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        BPromedio = new javax.swing.JButton();
        BMinMax = new javax.swing.JButton();
        BCantidad = new javax.swing.JButton();
        PGrafica = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TQuery = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Insulina - Paciente ausl120130a");
        setPreferredSize(new java.awt.Dimension(900, 700));

        PEncabezado.setLayout(new java.awt.BorderLayout());

        LTitulo.setBackground(new java.awt.Color(0, 51, 102));
        LTitulo.setForeground(new java.awt.Color(255, 255, 255));
        LTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        LTitulo.setText("   Graficacion de Valores de una BD");
        LTitulo.setOpaque(true);
        LTitulo.setPreferredSize(new java.awt.Dimension(900, 60));
        PEncabezado.add(LTitulo, java.awt.BorderLayout.NORTH);

        jToolBar1.setRollover(true);
        jToolBar1.setPreferredSize(new java.awt.Dimension(900, 45));

        BPromedio.setText("PROMEDIO MENSUAL");
        BPromedio.setFocusable(false);
        BPromedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BPromedioActionPerformed(evt);
            }
        });
        jToolBar1.add(BPromedio);

        BMinMax.setText("MINIMO Y MAXIMO");
        BMinMax.setFocusable(false);
        BMinMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMinMaxActionPerformed(evt);
            }
        });
        jToolBar1.add(BMinMax);

        BCantidad.setText("CANTIDAD DE MEDICIONES");
        BCantidad.setFocusable(false);
        BCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCantidadActionPerformed(evt);
            }
        });
        jToolBar1.add(BCantidad);

        PEncabezado.add(jToolBar1, java.awt.BorderLayout.CENTER);

        getContentPane().add(PEncabezado, java.awt.BorderLayout.NORTH);

        PGrafica.setBackground(new java.awt.Color(153, 153, 153));
        PGrafica.setLayout(new java.awt.BorderLayout());
        getContentPane().add(PGrafica, java.awt.BorderLayout.CENTER);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(900, 195));

        TQuery.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TQuery.setRowHeight(24);
        jScrollPane1.setViewportView(TQuery);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BPromedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BPromedioActionPerformed
        // Pasos 8 y 10: consulta para un paciente en particular.
        String query = "SELECT DATE_FORMAT(fecha, '%Y-%m') AS periodo, "
                + "ROUND(AVG(nvl_glucosa), 2) AS promedio "
                + "FROM mediciones "
                + "WHERE pacientes_curp = 'ausl120130a' "
                + "GROUP BY DATE_FORMAT(fecha, '%Y-%m') "
                + "ORDER BY periodo";

        datos = cnx.consultar(query);
        series.clear(); // Evita duplicar las series al volver a pulsar.
        series.add("Promedio");

        // Paso 11: agregar la grafica al panel y entablar la misma consulta.
        PGrafica.removeAll();
        chart = new GraficaXY("Promedio mensual de glucosa del paciente ausl120130a",
                "Mes", "Nivel de glucosa", series, datos);
        PGrafica.add(chart.chartPanel);
        PGrafica.updateUI();
        cnx.entablar(query, TQuery, 60, 40);
    }//GEN-LAST:event_BPromedioActionPerformed

    private void BMinMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMinMaxActionPerformed
        // Pasos 8 y 10: consulta para un paciente en particular.
        String query = "SELECT DATE_FORMAT(fecha, '%Y-%m') AS periodo, "
                + "MIN(nvl_glucosa) AS minimo, MAX(nvl_glucosa) AS maximo "
                + "FROM mediciones "
                + "WHERE pacientes_curp = 'ausl120130a' "
                + "GROUP BY DATE_FORMAT(fecha, '%Y-%m') "
                + "ORDER BY periodo";

        datos = cnx.consultar(query);
        series.clear(); // Evita duplicar las series al volver a pulsar.
        series.add("Minimo");
        series.add("Maximo");

        // Paso 11: agregar la grafica al panel y entablar la misma consulta.
        PGrafica.removeAll();
        chart = new GraficaXY("Minimo y maximo mensual de glucosa del paciente ausl120130a",
                "Mes", "Nivel de glucosa", series, datos);
        PGrafica.add(chart.chartPanel);
        PGrafica.updateUI();
        cnx.entablar(query, TQuery, 40, 30, 30);
    }//GEN-LAST:event_BMinMaxActionPerformed

    private void BCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCantidadActionPerformed
        // Pasos 8 y 10: consulta para un paciente en particular.
        String query = "SELECT DATE_FORMAT(fecha, '%Y-%m') AS periodo, "
                + "COUNT(*) AS cantidad "
                + "FROM mediciones "
                + "WHERE pacientes_curp = 'ausl120130a' "
                + "GROUP BY DATE_FORMAT(fecha, '%Y-%m') "
                + "ORDER BY periodo";

        datos = cnx.consultar(query);
        series.clear(); // Evita duplicar las series al volver a pulsar.
        series.add("Mediciones");

        // Paso 11: agregar la grafica al panel y entablar la misma consulta.
        PGrafica.removeAll();
        chart = new GraficaXY("Cantidad de mediciones por mes del paciente ausl120130a",
                "Mes", "Cantidad", series, datos);
        PGrafica.add(chart.chartPanel);
        PGrafica.updateUI();
        cnx.entablar(query, TQuery, 60, 40);
    }//GEN-LAST:event_BCantidadActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() { new FConsultas().setVisible(true); }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCantidad;
    private javax.swing.JButton BMinMax;
    private javax.swing.JButton BPromedio;
    private javax.swing.JLabel LTitulo;
    private javax.swing.JPanel PEncabezado;
    private javax.swing.JPanel PGrafica;
    private javax.swing.JTable TQuery;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}

