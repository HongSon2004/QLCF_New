
package entity;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class PanelLogin extends javax.swing.JPanel {

  
    public PanelLogin() {
        initComponents();
        setOpaque(false);
    }
    
    
    @SuppressWarnings("uncheck")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    protected void panelComponent(Graphics qrphcs){
        Graphics2D g2 = (Graphics2D) qrphcs;
        GradientPaint gra = new GradientPaint(0,0,new Color(151,97,87),0,getHeight(),new Color(151, 98, 87));
        g2.setPaint(gra);
        g2.fillRect(0,0,getWidth(), getHeight());
        super.paintComponent(qrphcs);
        
    }  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
