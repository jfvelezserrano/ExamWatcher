package com.gavab.examwatcher;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;

import java.io.IOException;
import javax.swing.JOptionPane;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jvelez
 */
public class ExamWatcher extends javax.swing.JFrame {

    static boolean WINDOWS_SYSTEM = false;

    private static void testOS() {
        String name = System.getProperty("os.name"); //NOI18N

        if (name.toLowerCase().contains("win")) { //NOI18N
            WINDOWS_SYSTEM = true;
        }
    }

    /**
     * Creates new form ExamReadyTester
     */
    public ExamWatcher() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        problemLabel = new javax.swing.JLabel();
        buttonSelectFolder = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("null");
        setAlwaysOnTop(true);
        setFocusCycleRoot(false);
        setIconImage((new javax.swing.ImageIcon(getClass().getResource("/com/gavab/examwatcher/ExamWatcher.png")).getImage()));
        setMinimumSize(new java.awt.Dimension(200, 200));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel2.setText("v 1.7 pan");

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle"); // NOI18N
        problemLabel.setText(bundle.getString("NO WARNINGS")); // NOI18N
        problemLabel.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(problemLabel)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(problemLabel)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        buttonSelectFolder.setText(bundle.getString("GENERATE EXAM DELIVERABLE")); // NOI18N
        buttonSelectFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectFolderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonSelectFolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSelectFolder)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    FinishExam finishDialog = new FinishExam(this,logMessages);

    private void buttonSelectFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelectFolderActionPerformed
        finishDialog.setVisible(true);
    }//GEN-LAST:event_buttonSelectFolderActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        String ObjButtons[] = {java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("EXIT"),java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("RETURN TO THE EXAM")};
        String message = java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("ALL YOUR LOG INFORMATION WILL BE LOST. ARE YOU SURE YOU WANT TO EXIT");
        String title = java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("ONLINE EXAMINATION SYSTEM");
        int PromptResult = JOptionPane.showOptionDialog(this,message,title,JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(PromptResult==JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private enum NetworkState {
        connected, unconnected, unknown
    };
    private static NetworkState state = NetworkState.unknown;
    private static ArrayList<String> logMessages = new ArrayList<>();

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
            java.util.logging.Logger.getLogger(ExamWatcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExamWatcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExamWatcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExamWatcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        testOS();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                final ExamWatcher form = new ExamWatcher();
                form.setVisible(true);
                //form.setResizable(false);

                int delay = 5000; //milliseconds
                ActionListener taskPerformer = new ActionListener() {
                    int cont = 0;

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        Color STOP = Color.getHSBColor(0f,1f,1f);
                        Color DANGER = Color.getHSBColor(0.7f,1f,1f);
                        Color OK = Color.getHSBColor(0.5f,1f,1f);
                        
                        form.setState(javax.swing.JFrame.NORMAL);
                                               
                        if (testConection("http://en.wikipedia.org/")) {
                            String forApplication = forbidenApplications();
                            if (!forApplication.equals(""))
                                violationActions(forApplication, STOP);
                            else
                                violationActions(java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("NETWORK CONNECTION DETECTED (TEST 1)"), DANGER);
                        } else if (testConection("http://216.58.211.196/")) {
                            String forApplication = forbidenApplications();
                            if (!forApplication.equals(""))
                                violationActions(forApplication, STOP);
                            else
                                violationActions(java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("NETWORK CONNECTION DETECTED (TEST 2)"), DANGER);
                        } else {
                            System.out.print(cont++);
                            form.jPanel1.setBackground(OK);
                            //System.out.println("  You are ready to do the exam");
                            form.problemLabel.setText(java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("YOU ARE READY TO DO THE EXAM"));

                            if (state != NetworkState.unconnected) {
                                String message = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
                                message += java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("READY TO DO THE EXAM");
                                logMessages.add(message);
                            }
                            state = NetworkState.unconnected;
                        }
                    }

                    private void violationActions(String errorType, Color color) {
                        System.out.print(cont++);
                        form.jPanel1.setBackground(color);
                        form.problemLabel.setText(errorType);

                        System.out.println("  "+ errorType);
                        if (state != NetworkState.connected) {
                            String message = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
                            message = message + " - " + errorType;
                            logMessages.add(message);
                        }
                        state = NetworkState.connected;
                    }

                    private boolean testConection(String conexion) {
                        BufferedReader in = null;
                        try {
                            URL url = new URL(conexion);
                            URLConnection urlcon = url.openConnection();
                            urlcon.setConnectTimeout(5000);
                            urlcon.setReadTimeout(5000);
                            urlcon.setUseCaches(false);

                            InputStream inputStream = urlcon.getInputStream();

                            in = new BufferedReader(
                                    new InputStreamReader(inputStream));

                            String inputLine = in.readLine();
                            in.close();
                            return true;
                        } catch (Exception ex) {
                            if (in != null) {
                                try {
                                    in.close();
                                } catch (IOException ex1) {
                                    Logger.getLogger(ExamWatcher.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }

                            form.jPanel1.setBackground(Color.yellow);
                            System.out.print(cont++);
                            //System.out.println("  You are unconnected");
                        }
                        return false;
                    }

                    private String forbidenApplications() {
                        try {
                            Process p;
                            if (WINDOWS_SYSTEM) {
                                p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
                            } else {
                                p = Runtime.getRuntime().exec("ps -e");
                            }
                            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                            String line = input.readLine();
                            while (line != null) {
                                if (line.toLowerCase().contains("iexplore.exe")) {
                                    System.out.println(line.toLowerCase());
                                    return java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("INTERNET EXPLORER DETECTED - CLOSE THE PROGRAM");
                                } else if (line.toLowerCase().contains("microsoftedge.exe")) {
                                    System.out.println(line.toLowerCase());
                                    return java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("MICROSOFT EDGE DETECTED - CLOSE THE PROGRAM");
                                } else if (line.toLowerCase().contains(" insync")) {
                                    System.out.println(line.toLowerCase());
                                    return java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("INSYNC DETECTED - CLOSE THE PROGRAM");
                                } else if (line.toLowerCase().contains("dropbox")) {
                                    System.out.println(line.toLowerCase());
                                    return java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("DROPBOX DETECTED - CLOSE THE PROGRAM");
                                } else if (line.toLowerCase().contains("megasync")) {
                                    System.out.println(line.toLowerCase());
                                    return java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("MEGASYNC DETECTED - CLOSE THE PROGRAM");
                                } else if (line.toLowerCase().contains("chrome")) {
                                    System.out.println(line.toLowerCase());
                                    return java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("CHROME DETECTED - CLOSE THE PROGRAM");
                                } else if (line.toLowerCase().contains("firefox")) {
                                    System.out.println(line.toLowerCase());
                                    return java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("FIREFOX DETECTED - CLOSE THE PROGRAM");
                                } else if (line.toLowerCase().contains("virtualbox")) {
                                    System.out.println(line.toLowerCase());
                                    return java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("VIRTUALBOX DETECTED - CLOSE THE PROGRAM");
                                } else if (line.toLowerCase().contains("vmware")) {
                                    System.out.println(line.toLowerCase());
                                    return java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("VMWARE DETECTED - CLOSE THE PROGRAM");
                                } else if (line.toLowerCase().contains("opera")) {
                                    System.out.println(line.toLowerCase());
                                    return java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("OPERA DETECTED - CLOSE THE PROGRAM");
                                }
                                line = input.readLine();
                            }
                            input.close();
                        } catch (Exception err) {
                            return java.util.ResourceBundle.getBundle("com/gavab/examwatcher/Bundle").getString("OTHER ERROR DETECTED");
                        }
                        return "";
                    }
                };
                new Timer(delay, taskPerformer).start();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSelectFolder;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel problemLabel;
    // End of variables declaration//GEN-END:variables

}
