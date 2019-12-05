package TrabalhoSO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class RoundRobin extends javax.swing.JFrame {

    int Contador; //contador do número total de processos
    int NProcessos; //Número de processos executando.
    int TempoP=0; //Tempo que o processo demora a ser executado
    int Quantum=-0; //tempo que o processo permanece sendo executado
    int TRest=0; // tempo que ainda falta para ser executado.
    int Durou;// tempo que o processo demorou a ser executado.
    int PTerminados; // número de processos terminados 

    public RoundRobin() {
        initComponents();
        jQuantum.grabFocus();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jAdicionar = new javax.swing.JButton();
        jQuantum = new javax.swing.JTextField();
        jTempoP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jRemover = new javax.swing.JButton();
        jIniciar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTInicial = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTFinal = new javax.swing.JTable();
        jGrafico = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jAdicionar.setText("Adicionar");
        jAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAdicionarActionPerformed(evt);
            }
        });

        jQuantum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jQuantumActionPerformed(evt);
            }
        });

        jTempoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTempoPActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel1.setText("Quantum:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel2.setText("Tempo do Processo:");

        jRemover.setText("Remover");
        jRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRemoverActionPerformed(evt);
            }
        });

        jIniciar.setText("Iniciar");
        jIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIniciarActionPerformed(evt);
            }
        });

        jTInicial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Proceso", "Tempo do Processo", "Quantum", "Tempo Restante", "Estado"
            }
        ));
        jScrollPane3.setViewportView(jTInicial);

        jTFinal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Proceso", "Tempo do Processo", "Quantum", "Tempo Final"
            }
        ));
        jScrollPane4.setViewportView(jTFinal);

        javax.swing.GroupLayout jGraficoLayout = new javax.swing.GroupLayout(jGrafico);
        jGrafico.setLayout(jGraficoLayout);
        jGraficoLayout.setHorizontalGroup(
            jGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        jGraficoLayout.setVerticalGroup(
            jGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 233, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRemover)
                        .addGap(18, 18, 18)
                        .addComponent(jIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jQuantum, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jTempoP, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jQuantum, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTempoP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAdicionarActionPerformed
        if((Integer.parseInt(jTempoP.getText())) <=100){
            Entrar();
            jQuantum.setEditable(false);
        }else{
            JOptionPane.showMessageDialog(null, "tempo do processo não pode ser maior que 100");
            jTempoP.setText(null);
            jTempoP.grabFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jAdicionarActionPerformed

    private void jQuantumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jQuantumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jQuantumActionPerformed

    private void jTempoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTempoPActionPerformed
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jTempoPActionPerformed

    private void jRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRemoverActionPerformed
            // TODO add your handling code here:
    }//GEN-LAST:event_jRemoverActionPerformed

    private void jIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIniciarActionPerformed
        new Thread(new Fio()).start(); //Cria um novo Fio
        Iniciar(); 
        // TODO add your handling code here:
    }//GEN-LAST:event_jIniciarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RoundRobin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoundRobin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoundRobin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoundRobin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoundRobin().setVisible(true);
            }
        });
    }
    int n;
    private class Fio implements Runnable{ //Objeto do tipo fio com extensão executavél
    @Override
    public void run(){
        n++;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart criaGrafico = ChartFactory.createBarChart("Processo","Nome", "Executando", dataset,PlotOrientation.VERTICAL,true,true,false);
        ChartPanel painel = new ChartPanel(criaGrafico);
        painel.setPreferredSize(new Dimension(460,233));
        jGrafico.setLayout(new BorderLayout());
        jGrafico.add(painel);
        setVisible(true);
        int i=0; // contador de while
        dataset.addValue(0, "Processo"+i, "Processo"+i);
        int estado=1; //Estado do laço while que indica se pode seguir ou não
        
        while(estado!=0){
            while(i<Contador){ //Passar pelas filas
                Carregar(i);
                if(TRest!=0 && TRest>Quantum){ //Executando os Processos
                    for(int c=1; c<=Quantum; c++){
                        jTInicial.setValueAt("Procesando",i,4);
                        TRest--;
                        dataset.setValue(1,"Processo"+i,"Processo"+i);
                        jTInicial.setValueAt(String.valueOf(TRest),i,3);
                        Durou++;
                        Dormir();
                    }
                    jTInicial.setValueAt("Espera",i,4);
                    dataset.setValue(0,"Processo"+i,"Processo"+i);
                    if(TRest==0){
                        jTInicial.setValueAt("Terminado",i,4);
                        Informe(i);
                        Apagar(i);
                       
                    }
            }else{
                if(TRest>0 && Quantum!=0){
                    while(TRest>0){
                        jTInicial.setValueAt("Procesando",i,4);
                        TRest--;
                        jTInicial.setValueAt(String.valueOf(TRest),i,3);
                        Durou++;
                        dataset.setValue(1,"Processo"+i,"Processo"+i);
                        Dormir();
                    }
                    jTInicial.setValueAt("Espera",i,4);
                    dataset.setValue(0,"Processo"+i,"Processo"+i);
                    if(TRest==0 && Quantum!=0){
                        jTInicial.setValueAt("Terminado",i,4);
                        Informe(i);
                        Apagar(i);
                        }
                    }else{
                        if(TRest==0 && Quantum!=0){
                            jTInicial.setValueAt("Terminado",i,4);
                            Informe(i);
                            Apagar(i);
                            
                        }
                    }
                }
                i++;
            }
            i=0;
           
            
        }
            
    }
}
    



    
public void Carregar(int i){ //Carrega os valores da Tabela
    NProcessos=(int)jTInicial.getValueAt(i,0);
    TempoP=parseInt((String)(jTInicial.getValueAt(i,1)));
    Quantum=parseInt((String)(jTInicial.getValueAt(i,2)));
    TRest=parseInt((String)(jTInicial.getValueAt(i,3)));
}    
    



public void Entrar (){ //O processo entra na tabela.
    DefaultTableModel modelo =(DefaultTableModel) jTInicial.getModel();

    Contador ++;
    Object[] Tabela = new Object[5];
    Tabela[0]= Contador;
    Tabela[1]= jTempoP.getText();
    Tabela[2]= jQuantum.getText();
    Tabela[3]= jTempoP.getText();
    Tabela[4]= "Pronto";
    modelo.addRow(Tabela);
    jTInicial.setModel(modelo);
    jTempoP.setText(null);
    jTempoP.grabFocus();
}



public void Dormir(){
    try{
        Thread.sleep(1000); //Dormir sistema
    }catch(InterruptedException ex){
        Logger.getLogger(RoundRobin.class.getName()).log(Level.SEVERE,null,ex);
    }

}    


public void Informe(int c){
    DefaultTableModel modelo2 = (DefaultTableModel) jTFinal.getModel();

    Object[] Tabela= new Object[5];
    Tabela[0]= c+1;
    Tabela[1]= TempoP;
    Tabela[2]= Quantum;
    Tabela[3]= Durou+" Segundos";
    Tabela[4]= "Terminado";
    modelo2.addRow(Tabela);
    jTFinal.setModel(modelo2);

    PTerminados++;
    
}    


public void Apagar(int c){ //Elimina os registros da tabela , processos
    jTInicial.setValueAt(0,c,0);
    jTInicial.setValueAt("0",c,1);
    jTInicial.setValueAt("0",c,2);
    jTInicial.setValueAt("0",c,3);
    jTInicial.setValueAt("******",c,4);
}    
    

public void Iniciar(){ //Inicia a sequência de processos
        jLabel2.setVisible(false);
        jLabel1.setVisible(false);
        jTempoP.setVisible(false);
        jQuantum.setVisible(false);
        jAdicionar.setVisible(false);
        jIniciar.setVisible(false);
}
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAdicionar;
    private javax.swing.JPanel jGrafico;
    private javax.swing.JButton jIniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jQuantum;
    private javax.swing.JButton jRemover;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTFinal;
    private javax.swing.JTable jTInicial;
    private javax.swing.JTextField jTempoP;
    // End of variables declaration//GEN-END:variables
}
