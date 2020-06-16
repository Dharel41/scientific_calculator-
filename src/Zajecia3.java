import org.mariuszgromada.math.mxparser.Expression;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.text.MessageFormat;

public class Zajecia3 extends JFrame {
    Container con = getContentPane();
    JMenuItem restart;
    JMenuItem exit;
    JTextArea area1;
    JButton button1;
    DefaultListModel<Operation> listModel;
    JList<Operation> lista1;
    JTextField field1;
    String getField1;
    Object source;
    Double result;

    public Zajecia3() {
        super("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(0, 0);
        setSize(1000, 800);
        setVisible(true);
        setLayout(null);

        button1 = new JButton("Evaluate!");
        button1.setBounds(880, 740, 100, 20);
        button1.addActionListener(action);
        con.add(button1);


        Operation dod = new Operation("Dodawanie", "dod");
        Operation odej = new Operation("Odejmowanie", "odej");
        Operation mnoz = new Operation("Mnozenie", "mnoz");
        Operation sin = new Operation("Sinus", "sin");
        Operation cos = new Operation("Cosinus", "cos");
        Operation tan = new Operation("Tangens", "tan");
        Operation log10 = new Operation("Logarytm Dziesietny", "log10");
        Operation sqrt = new Operation("Pierwiastek", "sqrt");
        Operation pi = new Operation("Liczba pi", "pi");
        Operation eul = new Operation("Liczba Eulera", "eul");
        Operation lap = new Operation("Limit Laplacea", "lap");
        Operation last=new Operation("Last result","last");
        listModel = new DefaultListModel<>();
        listModel.addElement(dod);
        listModel.addElement(odej);
        listModel.addElement(mnoz);
        listModel.addElement(sin);
        listModel.addElement(cos);
        listModel.addElement(tan);
        listModel.addElement(log10);
        listModel.addElement(sqrt);
        listModel.addElement(pi);
        listModel.addElement(eul);
        listModel.addElement(lap);
        listModel.addElement(last);
        lista1 = new JList<>(listModel);
        lista1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista1.addListSelectionListener(listSelection);
        lista1.setBounds(720, 30, 220, 670);
        con.add(lista1);


        field1 = new JTextField();
        field1.setBounds(0, 720, 700, 30);
        field1.addKeyListener(keyListener);
        con.add(field1);


        JMenu menu1 = new JMenu("Options");
        menu1.setBounds(0, 0, 65, 20);
        restart = (new JMenuItem("Restart"));
        exit = new JMenuItem("Exit");
        exit.addActionListener(action);
        restart.addActionListener(action);
        menu1.add(restart);
        menu1.add(exit);


        JMenuBar menuBar1 = new JMenuBar();
        menuBar1.setBounds(0, 0, 940, 30);
        menuBar1.add(menu1);
        con.add(menuBar1);


        area1 = new JTextArea();
        area1.setBounds(0, 30, 700, 670);
        area1.setEditable(false);
        area1.setLineWrap(true);
        con.add(area1);

    }

    ActionListener action=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            source = e.getSource();

            if (source == exit) {
                System.exit(0);
            }
            if (source == restart) {
                area1.setText("");
            }
            if (source == button1) {
                getField1 = field1.getText();
                Expression expression = new Expression(field1.getText());
                if (expression.checkSyntax()) {
                    result = expression.calculate();
                    String results= MessageFormat.format("{0} = {1}\n",getField1,result);
                    area1.append(results);

                } else {
                    String errorMessage = expression.getErrorMessage();
                    JOptionPane.showMessageDialog(null,errorMessage);
                }
                     field1.setText("");

            }

        }

    };

  ListSelectionListener listSelection=new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
          int index = lista1.getSelectedIndex();
          if(index==0){
              field1.setText(" +");
          }
          if(index==1){
              field1.setText(" -");
          }
          if(index==2){
              field1.setText(" *");
          }
          if(index==3){
              field1.setText("sin()");
          }
          if(index==4){
              field1.setText("cos()");
          }
          if(index==5){
              field1.setText("tan()");
          }
          if(index==6){
              field1.setText("log10()");
          }
          if(index==7){
              field1.setText("sqrt()");
          }
          if(index==8){
              field1.setText("pi");
          }
          if(index==9){
              field1.setText("e");
          }
          if(index==10){
              field1.setText("[Ll]");
          }
          if(index==11){
              field1.setText(result+"");
          }
      }


  };

  KeyListener keyListener=new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {

      }

      @Override
      public void keyPressed(KeyEvent e) {
          if(e.getKeyCode()==KeyEvent.VK_UP) {
              field1.setText(getField1);
          }
          if(e.getKeyCode()==KeyEvent.VK_ENTER){
              button1.doClick();
          }
      }

      @Override
      public void keyReleased(KeyEvent e) {

      }
  };


    public static void main(String[] args){
                new Zajecia3();
    }
}
