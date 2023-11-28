/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

/**
 *
 * @author mtqua
 */
public class Message extends javax.swing.JPanel {

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    private MessageType messageType = MessageType.SUCCESS;
    private boolean show;

    public Message() {
        initComponents();
        setOpaque(false);
        setVisible(false);
    }

    public void showMessage(MessageType messageType, String message) {
        this.messageType = messageType;
        lbMessage.setText(message);
        if (messageType == MessageType.SUCCESS) {
            lbMessage.setIcon(new ImageIcon(getClass().getResource("/coffee/icon/success.png")));
        } else {
            lbMessage.setIcon(new ImageIcon(getClass().getResource("/coffee/icon/error.png")));
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbMessage = new javax.swing.JLabel();

        lbMessage.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lbMessage.setText("Messenger");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(lbMessage)
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbMessage)
                .addGap(0, 10, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        if (messageType == MessageType.SUCCESS) {
            g2.setColor(new Color(15, 174, 37));
        } else {
            g2.setColor(new Color(240, 52, 53));
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setComposite(AlphaComposite.SrcOver);
        g2.setColor(new Color(245, 245, 245));
        g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        super.paintComponent(grphcs);
    }

    public static enum MessageType {
        SUCCESS, ERROR
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbMessage;
    // End of variables declaration//GEN-END:variables
}
