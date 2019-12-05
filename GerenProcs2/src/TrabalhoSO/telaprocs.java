
package TrabalhoSO;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class telaprocs extends javax.swing.JFrame {
   ArrayList<Processo> Grafico = new ArrayList();
   Processo B = new Processo(); 
   public telaprocs() 
    {
        initComponents();
        clockContador.start();
    }
    int n = 0;
    int clock = 0;
    ArrayList<Processo> T1Processos = new ArrayList();
    public void adicionarNoJTable() 
    {
        DefaultTableModel listaprocs1 = (DefaultTableModel) jTProcessos.getModel();
        Object rowData[] = new Object[3]; //Tabela 1 ->[0] = coluna 1,[1] - c2,[3] - c3.
            for (int i = 0; i < T1Processos.size(); i++) 
            {
                if (i == T1Processos.size() - 1) 
                {
                    rowData[0] = T1Processos.get(i).getnome();
                    rowData[1] = T1Processos.get(i).gettempo();
                    rowData[2] = T1Processos.get(i).getPID();
                    listaprocs1.addRow(rowData); 
                }
                
            }
    }
    Thread clockContador = new Thread() //Contador Clock;
    {
        public void run() 
        {
            while (true) 
            {
                clock++;
                try 
                {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) 
                {
                    Logger.getLogger(telaprocs.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };
    public void FuncaoPrincipal()
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList Ordenador = new ArrayList();
        JFreeChart criaGrafico = ChartFactory.createBarChart("Processo","Nome", "Tempo", dataset,PlotOrientation.VERTICAL,true,true,false);
        ChartPanel painel = new ChartPanel(criaGrafico);
        painel.setPreferredSize(new Dimension(400,400));
        jGrafico.setLayout(new BorderLayout());
        jGrafico.add(painel);
        setVisible(true);
        if(OpcoesPro.getSelectedItem().equals("FIFO")) //Escolheu FIFO
        {
        DefaultTableModel Terminados = (DefaultTableModel) Tterminados.getModel();
        DefaultTableModel listaProcsEmExec = (DefaultTableModel) jTExecutaveis.getModel();
        Object rowData[] = new Object[3]; //Tabela 2 ->[0] = coluna 1,[1] - c2,[3] - c3.
        int agendaprocsparado = T1Processos.size();
            
            for (int i = 0; i < T1Processos.size(); i++) 
            {
                rowData[0] = T1Processos.get(i).getnome();
                rowData[1] = T1Processos.get(i).gettempo();
                rowData[2] = T1Processos.get(i).getPID();
                listaProcsEmExec.addRow(rowData);
                 dataset.addValue(0,(String)rowData[0],(String)rowData[0]);
                Ordenador.add(rowData);
            }
            Thread executando = new Thread() 
            {
                public void run() 
                {
                    DefaultTableModel listaProcExecutando = (DefaultTableModel) jTExecutando.getModel();
                    Object rowData1[] = new Object[3]; //Tabela 3 ->[0] = coluna 1,[1] - c2,[3] - c3. 
                    for (int u = 0; u < agendaprocsparado; u++)
                    {
                        rowData1[0] = T1Processos.get(0).getnome();
                        rowData1[1] = T1Processos.get(0).gettempo();
                        rowData1[2] = T1Processos.get(0).getPID();
                        listaProcExecutando.addRow(rowData1);
                        dataset.setValue(1,(String)rowData1[0],(String)rowData1[0]);
                        int clockparado = clock;
                        while (clock < (clockparado + T1Processos.get(0).gettempo())) 
                        {
                            try 
                            {
           
                                JFreeChart criaGrafico = ChartFactory.createBarChart("Processo","Nome", "Tempo", dataset,PlotOrientation.VERTICAL,true,true,false);
                                ChartPanel painel = new ChartPanel(criaGrafico);
                                painel.setPreferredSize(new Dimension(400,400));
                                jGrafico.setLayout(new BorderLayout());
                                jGrafico.add(painel);
                                setVisible(true);
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) 
                            {
                                Logger.getLogger(telaprocs.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        dataset.setValue(0,(String)rowData1[0],(String)rowData1[0]);
                        Terminados.addRow(rowData1);
                        T1Processos.remove(0);
                        listaProcExecutando.removeRow(0);
                        int agendaprocsparado1 = T1Processos.size();
                        listaProcsEmExec.removeRow(0);
                        for (int i = 0; i < agendaprocsparado1; i++) 
                        {
                            rowData[0] = T1Processos.get(i).getnome();
                            rowData[1] = T1Processos.get(i).gettempo();
                            rowData[2] = T1Processos.get(i).getPID();
                        }
                    }
                }
            };
        executando.start(); //Inicia Thread
    }
    if(OpcoesPro.getSelectedItem().equals("SJF")) // Escolheu SJF
    {
        DefaultTableModel Terminados = (DefaultTableModel) Tterminados.getModel();
        DefaultTableModel listaProcsEmExec = (DefaultTableModel) jTExecutaveis.getModel();
        ArrayList<Processo> agendaprocs1 = new ArrayList();
        ArrayList<Processo> agendaprocsSJF = new ArrayList();
        agendaprocs1 = T1Processos;
        Object rowData[] = new Object[3];
        
        int aux = 0;
        while(aux<=agendaprocs1.size()) //A função pega o menor tempo do array 1 e depois reseta o aux e tira o menor tempo e adiciona
        {                               //no array 2, depois repete ate o array 1 esvaziar
            int t = 1000;
            for(int i = 0; i<agendaprocs1.size(); i++)
            {    
                if(agendaprocs1.get(i).gettempo()<t) //EX: processo 1 - 4 seg: t = 4,p2 - 3 s:t=4,3<4? então t = 3
                {
                    t = agendaprocs1.get(i).gettempo(); //Passa pelo agendaprocs e pego o que tem menor tempo
                } 
            }
            
            int i = 0;
            while(i<agendaprocs1.size())
            {
                if(agendaprocs1.get(i).gettempo()==t)
                {
                    agendaprocsSJF.add(agendaprocs1.get(i));   //passa pelo agenda procs de o tempo for igual a t retira e adiciona no agendaprocsSJF
                    agendaprocs1.remove(i);                    //sai com i == 1000
                    i=1000;
                    aux = 0;                                   //aux deve ser resetado ja que o tamanho mudou
                }
                i++;
            }
            aux++;
        }        
        for(int i = 0; i<agendaprocsSJF.size(); i++) //adiciona na Tabela 2
        {            
            rowData[0] = agendaprocsSJF.get(i).getnome();
            rowData[1] = agendaprocsSJF.get(i).gettempo();
            rowData[2] = agendaprocsSJF.get(i).getPID();
            listaProcsEmExec.addRow(rowData);
            dataset.addValue(0,(String)rowData[0],(String)rowData[0]);
        }
        
        int agendaprocsparado = agendaprocsSJF.size();
        Thread executando1 = new Thread() 
        {                  
            public void run() 
            {                             
                for(int u = 0; u < agendaprocsparado; u++)//Adiciona na Tabela 3
                {
                
                    DefaultTableModel listaProcExecutando = (DefaultTableModel) jTExecutando.getModel();
                    Object rowData1[] = new Object[3];
                    rowData1[0] = agendaprocsSJF.get(0).getnome();
                    rowData1[1] = agendaprocsSJF.get(0).gettempo();
                    rowData1[2] = agendaprocsSJF.get(0).getPID();
                    listaProcExecutando.addRow(rowData1);
                    dataset.setValue(1,(String)rowData1[0],(String)rowData1[0]);
                    int clockparado = clock;
                    System.out.println("clock: "+clockparado);
                    System.out.println("clockqtermina: "+(clockparado+agendaprocsSJF.get(0).gettempo()));
                    while(clock < (clockparado+agendaprocsSJF.get(0).gettempo())) //Espera o clock ser igual ao clock+tempo de exec. do processo
                    {
                        System.out.println("...");
                    }
                    agendaprocsSJF.remove(0);
                    dataset.setValue(0,(String)rowData1[0],(String)rowData1[0]);
                    Terminados.addRow(rowData1);
                    listaProcExecutando.removeRow(0);
                    int agendaprocsparado1 = agendaprocsSJF.size();
                    listaProcsEmExec.removeRow(0);
                    for(int i = 0; i<agendaprocsparado1; i++)
                    {
                        rowData[0] = agendaprocsSJF.get(i).getnome();
                        rowData[1] = agendaprocsSJF.get(i).gettempo(); 
                        rowData[2] = agendaprocsSJF.get(i).getPID();
                    }
                }                
            }
        };
        executando1.start();
    }
    if(OpcoesPro.getSelectedItem().equals("RR"))// Escolheu RR
    {
        DefaultTableModel Executaveis = (DefaultTableModel) jTExecutaveis.getModel();
        //agendaproce = T1Processos; //T1Processos é o agendaprocs, ou seja,processos da tabela 1
        int Quantum=Integer.parseInt(Quantum1.getText());//Tempo que cada processo tem pra executar
        Object RowData[] = new Object[3];
        for(int o=0;o<T1Processos.size();o++)
        {
            RowData[0] = T1Processos.get(o).getnome();
            dataset.addValue(0,(String)RowData[0],(String)RowData[0]);
        }
        for (int i = 0; i < T1Processos.size(); i++) 
            {
                RowData[0] = T1Processos.get(i).getnome();
                RowData[1] = T1Processos.get(i).gettempo();
                RowData[2] = T1Processos.get(i).getPID();
                Executaveis.addRow(RowData);
            }
        Thread executando = new Thread() 
        {
          public void run()
          {
              DefaultTableModel Terminados = (DefaultTableModel) Tterminados.getModel();
              DefaultTableModel Executando = (DefaultTableModel) jTExecutando.getModel();
              Object RowData2[] = new Object[3];
                    while(true)
                    {
                        RowData2[0] = T1Processos.get(0).getnome();
                        RowData2[1] = T1Processos.get(0).gettempo();
                        RowData2[2] = T1Processos.get(0).getPID();
                        Executando.addRow(RowData2);
                        dataset.setValue(1,(String)RowData[0],(String)RowData2[0]);
                        int clockparado = clock;
                        while ( clock<(clockparado+Quantum)) 
                        {
                            Thread atualiza = new Thread()
                            {
                                public void run() //Thread que fica atualizando o tempo , o objetivo agora é passar o processo
                                {                 //que tava em execução e terminou o quantum dele e sobrou tempo ainda ir pra tabela 2
                                                  //tipo o processo 1 tem tempo 5 e o quantum é 3, dai ele sobra 2 de tempo ainda,logo deve voltar pra tabela de executaveis
                                    Executando.removeRow(0);
                                    Executando.addRow(RowData2);
                                }
                            };atualiza.start();
                            try 
                            {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) 
                            {
                                Logger.getLogger(telaprocs.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        dataset.setValue(0,(String)RowData2[0],(String)RowData2[0]);
                        T1Processos.remove(0);
                        Executando.removeRow(0);                    
                        Executaveis.removeRow(0);
                            if((Integer)RowData2[1]-Quantum>0)
                            {
                                RowData2[1]=(Integer)RowData2[1]-Quantum;
                                Executaveis.addRow(RowData2);
                            }
                            else
                            {
                                Terminados.addRow(RowData2);
                            }
                        if(T1Processos.isEmpty())
                        {
                            break;
                        }
                    }
              
          }
        };executando.start();
        
    }
    }                                        

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addProcs = new javax.swing.JButton();
        Retirar = new javax.swing.JButton();
        clockvisualizacao = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTProcessos = new javax.swing.JTable();
        tempo = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTExecutaveis = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTExecutando = new javax.swing.JTable();
        OpcoesPro = new javax.swing.JComboBox<String>();
        jGrafico = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tterminados = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Quantum1 = new javax.swing.JTextField();
        clockvisualizacao1 = new javax.swing.JButton();
        clockvisualizacao2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addProcs.setText("Novo Proces.");
        addProcs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProcsActionPerformed(evt);
            }
        });

        Retirar.setText("Remover Proces.");
        Retirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetirarActionPerformed(evt);
            }
        });

        clockvisualizacao.setText("Clock");
        clockvisualizacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clockvisualizacaoActionPerformed(evt);
            }
        });

        jTProcessos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Processo", "Tempo", "PID"
            }
        ));
        jScrollPane1.setViewportView(jTProcessos);

        tempo.setText("2");
        tempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tempoActionPerformed(evt);
            }
        });

        jTExecutaveis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Processo", "Tempo", "PID"
            }
        ));
        jScrollPane2.setViewportView(jTExecutaveis);

        jButton2.setText("INICIAR!");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTExecutando.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Processo", "Tempo", "PID"
            }
        ));
        jScrollPane3.setViewportView(jTExecutando);

        OpcoesPro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FIFO", "SJF", "RR" }));
        OpcoesPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpcoesProActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jGraficoLayout = new javax.swing.GroupLayout(jGrafico);
        jGrafico.setLayout(jGraficoLayout);
        jGraficoLayout.setHorizontalGroup(
            jGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
        );
        jGraficoLayout.setVerticalGroup(
            jGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );

        Tterminados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Processo", "Tempo", "PID"
            }
        ));
        jScrollPane4.setViewportView(Tterminados);

        jLabel2.setText("Criados");

        jLabel3.setText("Executaveis");

        jLabel4.setText("Executando");

        jLabel5.setText("Terminados");

        Quantum1.setText("3");
        Quantum1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Quantum1ActionPerformed(evt);
            }
        });

        clockvisualizacao1.setText("Quantum");
        clockvisualizacao1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clockvisualizacao1ActionPerformed(evt);
            }
        });

        clockvisualizacao2.setText("Tempo");
        clockvisualizacao2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clockvisualizacao2ActionPerformed(evt);
            }
        });

        jButton1.setText("RR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(223, 223, 223)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addProcs)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Retirar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(clockvisualizacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clockvisualizacao1)
                                .addGap(8, 8, 8)
                                .addComponent(Quantum1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clockvisualizacao2)
                                .addGap(8, 8, 8)
                                .addComponent(tempo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(OpcoesPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(54, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addProcs)
                            .addComponent(Retirar)
                            .addComponent(jButton2)
                            .addComponent(OpcoesPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clockvisualizacao)
                            .addComponent(Quantum1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clockvisualizacao1)
                            .addComponent(clockvisualizacao2)
                            .addComponent(tempo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5))
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addProcsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProcsActionPerformed
        n++;
        Processo a = new Processo();
        a.setnome("processo " + n);
        a.setPID(n);
        //--------
        String tempoS = tempo.getText();
        a.settempo(Integer.parseInt(tempoS));
        //--------
        T1Processos.add(a);
            adicionarNoJTable();
    }//GEN-LAST:event_addProcsActionPerformed

    private void RetirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetirarActionPerformed
        DefaultTableModel RetirarProcesso = (DefaultTableModel) jTProcessos.getModel();
        //RetirarProcesso.removeRow(0); 
        if (T1Processos.size() != 0) 
        {
            RetirarProcesso.removeRow(0);
            T1Processos.remove(0);
        }
    }//GEN-LAST:event_RetirarActionPerformed

    private void clockvisualizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clockvisualizacaoActionPerformed

        Thread tclock = new Thread() 
        {
            public void run() 
            {
                while (true) 
                {
                    clockvisualizacao.setText("" + clock);
                }
            }
        };

        tclock.start();       // TODO add your handling code here:
    }//GEN-LAST:event_clockvisualizacaoActionPerformed

    private void tempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tempoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tempoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        FuncaoPrincipal();//INICIAR 
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void OpcoesProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpcoesProActionPerformed
   
    }//GEN-LAST:event_OpcoesProActionPerformed

    private void Quantum1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Quantum1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Quantum1ActionPerformed

    private void clockvisualizacao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clockvisualizacao1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clockvisualizacao1ActionPerformed

    private void clockvisualizacao2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clockvisualizacao2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clockvisualizacao2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        RoundRobin rr = new RoundRobin();
        rr.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(telaprocs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaprocs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaprocs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaprocs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new telaprocs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> OpcoesPro;
    private javax.swing.JTextField Quantum1;
    private javax.swing.JButton Retirar;
    private javax.swing.JTable Tterminados;
    private javax.swing.JButton addProcs;
    private javax.swing.JButton clockvisualizacao;
    private javax.swing.JButton clockvisualizacao1;
    private javax.swing.JButton clockvisualizacao2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jGrafico;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTExecutando;
    private javax.swing.JTable jTExecutaveis;
    private javax.swing.JTable jTProcessos;
    private javax.swing.JTextField tempo;
    // End of variables declaration//GEN-END:variables
}
