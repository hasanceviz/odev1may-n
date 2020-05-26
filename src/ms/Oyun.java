package ms;
import javax.swing.JToggleButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author pc
 */
public class Oyun extends javax.swing.JFrame {
    /*
    -2: opened but no bomb
    -1: has a bomb
    0: not open
    1 - 8: number of bombs
    */
    final int genislik = 9, yukseklik =9, bomba=5;
    JToggleButton[][] blok = new JToggleButton[yukseklik][genislik];
    int[][] blox = new int[yukseklik][genislik];
    boolean first, canPlay;
    
    ActionListener listen = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            int i = 0, j = 0;
            boolean found = false;
            for(i = 0; i < yukseklik; i++){
                for(j = 0; j < genislik; j++){
                    if(e.getSource() == blok[i][j]){
                        found = true;
                        break;
                    }
                }
                if(found) break;
            }
            if(canPlay){
                blok[i][j].setSelected(true);
                if(!first){
                    spawn(i, j);
                    first = true;
                }
                if(blox[i][j]!= -1 ){
                    open(i,j);
                    reval();
                } else lose();
                checkWin();
            } else reval();
        }
    };
    
    private void checkWin(){
        boolean won = true;
        for(int i = 0; i < yukseklik; i++){
            for(int j = 0; j < genislik; j++){
                if(blox[i][j] == 0) {
                    won = false;
                    break;
                }
            }
            if(!won) break;
        }
        if(won){
            javax.swing.JOptionPane.showMessageDialog(null, "Tebrikler Aşıyı Buldunuz :)");
            canPlay=false;
        }
    }
    
    private void lose(){
        canPlay = false;
        for(int i = 0; i < yukseklik; i++){
            for(int j = 0; j < genislik; j++){
                if(blox[i][j] == -1) {
                    blok[i][j].setText("corona");
                    blok[i][j].setSelected(true);
                }
            }
        }
    }
    
    private void open(int y, int x){
        if(y < 0 || x < 0 || x > genislik - 1 || y > yukseklik - 1 || blox[y][x] != 0) return;
        int bombs = 0;
        for(int i = y - 1; i <= y + 1;i++){
            for(int j = x - 1; j <= x + 1;j++){
                if(!(j < 0 || i < 0 || j > genislik - 1 || i > yukseklik - 1) && blox[i][j] == -1)
                    bombs++;
            }
        }
        if(bombs == 0){
            blox[y][x] = -2;
            for(int i = y - 1; i <= y + 1;i++){
                for(int j = x - 1; j <= x + 1;j++){
                    if(!(j < 0 || i < 0 || j > genislik - 1 || i > yukseklik - 1))
                    if(i != y || j != x) open(i,j);
                }
            }
        } else blox[y][x] = bombs;
    }
    
    
    private void reval(){
        for(int i = 0; i < yukseklik; i++){
            for(int j = 0; j < genislik; j++){
                if(blox[i][j] == 0){
                    blok[i][j].setText("");
                    blok[i][j].setSelected(false);

                }
                if(blox[i][j] == -2){
                    blok[i][j].setText("");
                    blok[i][j].setSelected(true);
                }
                if(blox[i][j] > 0){
                    blok[i][j].setText(""+blox[i][j]);
                    blok[i][j].setSelected(true);
                }
                if(!canPlay && blox[i][j] == -1) blok[i][j].setSelected(true);
            }
        }
        jPanel1.repaint();
    }
    
    private void spawn(int y, int x){
        for(int k = 1; k <= bomba;k++){
            int i, j;
            do{
                i = (int)(Math.random()*(genislik-.01));
                j = (int)(Math.random()*(yukseklik-.01));
            }
            while(blox[i][j] == -1 || (i == y && j == x));
            blox[i][j] = -1;
            
        }
    }
    
    public Oyun() {
        initComponents();
        for(int i = 0; i < yukseklik; i++){
            for(int j = 0; j < genislik; j++){
                blok[i][j] = new JToggleButton();
                blok[i][j].setSize(jPanel1.getWidth()/genislik, jPanel1.getHeight()/yukseklik);
                jPanel1.add(blok[i][j]);
                blok[i][j].setLocation(j*jPanel1.getWidth()/genislik, i*jPanel1.getHeight()/yukseklik);
                blok[i][j].addActionListener(listen);
            }
        }
        first = false;
        canPlay = true;
    }
    
    private void resiz(){
        for(int i = 0; i < yukseklik; i++){
            for(int j = 0; j < genislik; j++){
                blok[i][j].setSize(jPanel1.getWidth()/genislik, jPanel1.getHeight()/yukseklik);
                jPanel1.add(blok[i][j]);
                blok[i][j].setLocation(j*jPanel1.getWidth()/genislik, i*jPanel1.getHeight()/yukseklik);
            }
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CORONA TARLASI");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 849, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );

        jMenuBar1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        jMenu1.setText("Oyun");
        jMenu1.setActionCommand("OYUN");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Yeni Oyun");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        blox = new int[yukseklik][genislik];
        reval();
        canPlay = true;
        first = false;
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Oyun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Oyun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Oyun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Oyun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Oyun().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables


}
