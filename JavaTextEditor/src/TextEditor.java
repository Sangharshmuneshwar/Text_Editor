import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar Menubar;

    JTextArea textArea;
    JMenu File, Edit;
    //menu items for file
    JMenuItem newFile,openFile,saveFile;

    //menu items for edit

    JMenuItem cut,copy,paste,selectAll,close;
    TextEditor(){
        frame = new JFrame();
        Menubar = new JMenuBar();
        File = new JMenu("File");
        Edit = new JMenu("Edit");

        newFile = new JMenuItem("new window");
        openFile = new JMenuItem("open file");
        saveFile = new JMenuItem("save file");

        //adding actionListner to file menuIntem
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        File.add(newFile);
        File.add(openFile);
        File.add(saveFile);

        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("select all");
        close = new JMenuItem("close");


        //adding actionlisner to all edit menuIntem
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        Edit.add(cut);
        Edit.add(copy);
        Edit.add(paste);
        Edit.add(selectAll);
        Edit.add(close);

        Menubar.add(File);
        Menubar.add(Edit);



        frame.setJMenuBar(Menubar);

        textArea = new JTextArea();
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textArea,BorderLayout.CENTER);

        //create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        frame.add(panel);

        frame.setBounds(0,0,400,400);

        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);


    }

    public void actionPerformed(ActionEvent ActionEvent){
        if(ActionEvent.getSource() == cut){
            textArea.cut();
        }
        if (ActionEvent.getSource()==copy){
            textArea.copy();
        }
        if (ActionEvent.getSource()==paste){
            textArea.paste();
        }
        if (ActionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        if (ActionEvent.getSource() == close){
            System.exit(0);
        }
        if (ActionEvent.getSource() == openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int choose = fileChooser.showOpenDialog(null);

            if(choose == JFileChooser.APPROVE_OPTION){
                //getting selected file
                File file = fileChooser.getSelectedFile();

                //get file path

                String path = file.getPath();

                try {
                 // file reader

                    FileReader reader = new FileReader(path);

                    BufferedReader BufferedReader = new BufferedReader(reader);
                    String SingleLine = "";
                    String CompletLine = "";

                    //now read content of the file

                    while ((SingleLine = BufferedReader.readLine())!=null){
                        CompletLine += SingleLine + "\n";
                    }
                    textArea.setText(CompletLine);

                }
                catch (FileNotFoundException FileNotFoundException){
                 FileNotFoundException.printStackTrace();
                }
                catch (IOException IOException){
                    IOException.printStackTrace();
                }

            }
        }

        if(ActionEvent.getSource() == saveFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int option = fileChooser.showSaveDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
                //create new file

                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                try{
                   FileWriter fileWriter = new FileWriter(file);
                   BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                   //write the text of textArea to the file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                  ioException.printStackTrace();
                }
            }
        }

        if(ActionEvent.getSource()== newFile){
            TextEditor TextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor TextEditor = new TextEditor();

    }
}