package jffile;

/**
 * Created : Apr 28, 2012
 *
 * @author pquiring
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javaforce.*;

public class UnmapNetworkShareDialog extends javax.swing.JDialog implements ActionListener {

  /**
   * Creates new form UnmapNetworkShare
   */
  public UnmapNetworkShareDialog(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    setPosition();
    Mappings.loadMaps();
    listShares();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    close = new javax.swing.JButton();
    scroll = new javax.swing.JScrollPane();
    buttons = new javax.swing.JPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Disconnect Mapped Share");
    setResizable(false);

    close.setText("Close");
    close.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        closeActionPerformed(evt);
      }
    });

    buttons.setLayout(new java.awt.GridBagLayout());
    scroll.setViewportView(buttons);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(scroll)
          .addGroup(layout.createSequentialGroup()
            .addGap(0, 315, Short.MAX_VALUE)
            .addComponent(close)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(close)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
    dispose();
  }//GEN-LAST:event_closeActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel buttons;
  private javax.swing.JButton close;
  private javax.swing.JScrollPane scroll;
  // End of variables declaration//GEN-END:variables

  private void listShares() {
    Mappings.Maps maps = Mappings.getMaps();
    for(int a=0;a<maps.map.length;a++) {
      JButton b = new JButton();
      String mount = maps.map[a].mount;
      String uri = maps.map[a].uri;
      int idx = uri.lastIndexOf('/');
      if (idx == -1) idx = 0; else idx++;
      b.setText(uri.substring(idx));
      b.setActionCommand(mount);
      b.putClientProperty("wine", maps.map[a].wineDrive);
      b.addActionListener(this);
      buttons.add(b);
    }
  }

  public void actionPerformed(ActionEvent ae) {
    String mount = ae.getActionCommand();
    //TODO : send msg jfDesktop.unmount
    ShellProcess sp = new ShellProcess();
    JButton b = (JButton)ae.getSource();
    String wineDrive = (String)b.getClientProperty("wine");
    if ((wineDrive != null) && (wineDrive.length() > 0) && !wineDrive.equals("null")) {
      String output = sp.run(new String[] {"rm", wineDrive}, true);
      if (sp.getErrorLevel() != 0) {
        FileApp.inDialog = true;
        JF.showError("Warning", "Failed to disconnect wine drive link");
        FileApp.inDialog = false;
      }
    }
    Mappings.delMap(mount);
    Mappings.saveMaps();
    dispose();
  }

  private void setPosition() {
    Rectangle s = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    Dimension d = getSize();
    setLocation(s.width/2 - d.width/2, s.height/2 - (d.height/2));
  }

  public static void main(String args[]) {
    if (args.length != 2) {
      System.out.println("Usage : junmapshare server share");
      System.exit(1);
    }
    Mappings.loadMaps();
    String uri = "smb://" + args[0] + "/" + args[1];
    Mappings.Maps maps = Mappings.getMaps();
    String wineDrive = null;
    String mount = null;
    for(int a=0;a<maps.map.length;a++) {
      if (maps.map[a].uri.equalsIgnoreCase(uri)) {
        wineDrive = maps.map[a].wineDrive;
        mount = maps.map[a].mount;
        break;
      }
    }
    if (mount == null) {
      System.out.println("Share is not mapped");
      System.exit(1);
    }
    //TODO : send msg to jfDesktop.unmount
    if (wineDrive != null) {
      ShellProcess sp = new ShellProcess();
      String output = sp.run(new String[] {"rm", wineDrive}, true);
      if (sp.getErrorLevel() != 0) {
        System.out.println("Warning : Failed to disconnect wine drive link");
      }
    }
    Mappings.delMap(uri);
    Mappings.saveMaps();
    System.exit(0);
  }
}
