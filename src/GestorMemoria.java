
import static java.lang.Integer.parseInt;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GestorMemoria extends javax.swing.JFrame {
    
    Hilo_Procesos _hiloProcesos=null;
    Hilo_Memoria _hiloMemoria=null;
    public static Proceso[] procesos=new Proceso[15];
    public static RamAsign[] ramBank=new RamAsign[0];
    public static ControlRam RAM=new ControlRam(0,0,0,0);
    public static DefaultTableModel Tabla1;
    public static DefaultTableModel Tabla2;
      
    public GestorMemoria() {
        initComponents();
        Tabla1 =(DefaultTableModel) jTable1.getModel();
        Tabla2 =(DefaultTableModel) jTable2.getModel();
        if(_hiloProcesos==null){
          _hiloProcesos = new Hilo_Procesos();  
        }
        if(_hiloMemoria==null){
          _hiloMemoria = new Hilo_Memoria();  
        }
        
        for(int p=0; p<15; p++){
            procesos[p]=new Proceso();
        }
        float countRow=Integer.valueOf(tfMtotal.getText())/200000;
        ramBank=new RamAsign[(int)Math.ceil(countRow)];
        for(int p=0; p<(int)Math.ceil(countRow); p++){
            ramBank[p]=new RamAsign();
            Object[] tabla2 = new Object[4];
            tabla2[0]= ramBank[p].BankSize;
            tabla2[1]= ramBank[p].BankUsed;
            tabla2[2]= ramBank[p].BankFree;
            tabla2[3]= ramBank[p].TitleProcess; 
            Tabla2.addRow(tabla2);
            Tabla2.fireTableDataChanged();
        }
        RAM=new ControlRam(Integer.valueOf(GestorMemoria.tfMtotal.getText()),Integer.valueOf(GestorMemoria.tfMtotal.getText()),0,ramBank.length);
    }
    
    void Matar_proceso(){
        int fila = jTable1.getSelectedRow();
        jTable1.setValueAt("0", fila, 2);
        jTable1.setValueAt("Terminado", fila, 3);        
    }
    
    public static boolean Verify(int _ram){
       boolean res=true;
       if( _ram>4000000 ){ 
           res=false;
       }
       if( jTable1.getRowCount() >= 15 ){ 
           res=false;
       }
       return res;
    }

    public static void main(String args[]) {
   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestorMemoria().setVisible(true);
            }
        });
    }
    
    public void Crear(){ //Crea proceso y se agrega a la tabla
        int key = jTable1.getRowCount();
        if(Verify(RAM.MemoryGlobal)){
            procesos[key]=new Proceso();
            procesos[key].SetValues(key, Integer.valueOf(tfMemoria.getText()), 0, "Espera",tfNombre.getText());
            Object[] newRow = new Object[4];
            newRow[0]= key+1;
            newRow[1]= procesos[key].Nombre;
            newRow[2]= procesos[key].Memoria;
            newRow[3]= procesos[key].Estado;
            Tabla1.addRow(newRow); 
            //limpiar textfield's
            tfNombre.setText(null);
            tfMemoria.setText(null);
            //reenfocar en textfield nombre para agregar otro
            tfNombre.grabFocus(); 
            //refrescar listado de procesos
            Tabla1.fireTableDataChanged();
        } else {
            JOptionPane.showMessageDialog(null, "Por favor no sobrepasar los recursos del sistema");
        }
        
        if(_hiloProcesos.getState().toString().contentEquals("NEW")){
           _hiloProcesos.start(); 
        }
        if(_hiloMemoria.getState().toString().contentEquals("NEW")){
           _hiloMemoria.start(); 
        }
    } 
    
    public static void setProcesosList(int _key){
        Tabla1.setValueAt(procesos[_key].Nombre, _key,1);
        Tabla1.setValueAt(procesos[_key].Memoria, _key,2);
        Tabla1.setValueAt(procesos[_key].Estado, _key,3); 
        Tabla1.fireTableDataChanged();
    }
    
    public static void setBlockMemory(int _key){
        tfMenuso.setText(String.valueOf(RAM.MemoryUsed));
        tfMdisponible.setText(String.valueOf(RAM.MemoryFree));
        Tabla2.setValueAt(ramBank[_key].BankUsed, _key,1);
        Tabla2.setValueAt(ramBank[_key].BankFree, _key,2);
        Tabla2.setValueAt(ramBank[_key].TitleProcess, _key,3); 
        Tabla2.fireTableDataChanged();
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        bCrear = new javax.swing.JButton();
        tfNombre = new javax.swing.JTextField();
        tfMemoria = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfMtotal = new javax.swing.JTextField();
        tfMenuso = new javax.swing.JTextField();
        tfMdisponible = new javax.swing.JTextField();
        bMatar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Gestor de Memoria");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Crear Nuevo Proceso");

        jLabel3.setText("Nombre");

        jLabel5.setText("Memoria");

        bCrear.setText("Crear ");
        bCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCrearActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Memoria KB", "Estado"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(tfMemoria))
                        .addGap(27, 27, 27)
                        .addComponent(bCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(bCrear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("MEMORIA KB");

        jLabel7.setText("Total");

        jLabel8.setText("En uso");

        jLabel9.setText("Disponible");

        tfMtotal.setText("2097152");
        tfMtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMtotalActionPerformed(evt);
            }
        });

        tfMenuso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMenusoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfMdisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfMenuso, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfMtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfMtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfMenuso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMdisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)))
        );

        bMatar.setText("Matar Proceso");
        bMatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMatarActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Memoria KB", "En Uso KB", "Disponible KB", "Procesos"
            }
        ));
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bMatar)
                                .addGap(43, 43, 43)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(bMatar)
                                .addGap(35, 35, 35)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearActionPerformed
        
        if(
            ((Integer.parseInt(tfMemoria.getText()))<=(parseInt(tfMtotal.getText())))){
            Crear();
        }else{
            JOptionPane.showMessageDialog(null, "Por favor no sobrepasar los recursos del sistema");
            tfMemoria.setText(null);
            tfMemoria.grabFocus();  
            }    
    }//GEN-LAST:event_bCrearActionPerformed

    private void tfMenusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMenusoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tfMenusoActionPerformed

    private void bMatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMatarActionPerformed
        
        Matar_proceso();
    }//GEN-LAST:event_bMatarActionPerformed

    private void tfMtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMtotalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCrear;
    private javax.swing.JButton bMatar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTable jTable2;
    public static javax.swing.JTextField tfMdisponible;
    private javax.swing.JTextField tfMemoria;
    public static javax.swing.JTextField tfMenuso;
    public static javax.swing.JTextField tfMtotal;
    private javax.swing.JTextField tfNombre;
    // End of variables declaration//GEN-END:variables
}
