package gui;

import domain.Airport;
import serialization.threads.AirportParserThread;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

public class MainWindow extends JFrame {
    private JButton addAirportBtn;
    private JButton openFileBtn;
    private JButton saveBtn;
    private JToolBar toolBar;
    private JLabel statusLabel;

    private JList<String> airportsList;
    private Optional<String> selectedFile = Optional.empty();

    private boolean fileLoaded = false;

    private List<Airport> airports;

    public MainWindow() {
        setTitle("Airport flight manager");
        setSize(500, 500);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Exiting");
                save();

                System.exit(0);
            }
        });

        setLayout(new BorderLayout());

        try {
            initWidgets();
            initContent();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void initWidgets() throws Exception {
        Container container = getContentPane();

        toolBar = new JToolBar();
        openFileBtn = new JButton("Open");
        openFileBtn.setSize(200, 50);

        openFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOpenFileClick();
            }
        });
        openFileBtn.setIcon(new ImageIcon(new URL("http://www.simplesoft.it/img/articles/java/toolBar/open.png")));

        toolBar.add(openFileBtn);

        addAirportBtn = new JButton("Add airport");
        addAirportBtn.setSize(300, 50);

        addAirportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddAirportClick();
            }
        });

        saveBtn = new JButton("Save");
        saveBtn.setSize(200, 50);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSaveClick();
            }
        });

        toolBar.addSeparator();
        toolBar.add(addAirportBtn);
        toolBar.add(saveBtn);
        toolBar.setFloatable(false);

        container.add(toolBar, BorderLayout.NORTH);
    }

    private void initContent() {
        if (selectedFile.isEmpty()) {
            statusLabel = new JLabel("No file selected");
            getContentPane().add(statusLabel);
        }

        initAirportsList();
    }

    private void initAirportsList() {
        airportsList = new JList<>();
        airportsList.setVisible(false);

        getContentPane().add(airportsList, BorderLayout.LINE_START);
    }

    private void updateContentString() {
        if (selectedFile.isEmpty()) statusLabel.setText("No file selected");
        else statusLabel.setVisible(false);
    }

    private void updateAirportsList(List<Airport> airports) {
        List<String> strs = new ArrayList<>();

        for (Airport a : airports)
            strs.add(a.getName());

        airportsList.setListData(strs.toArray(String[]::new));
        airportsList.setVisible(true);
    }

    private void save() {

    }

    private void onOpenFileClick() {
        System.out.println("Open file");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a file to open");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Xml files", "xml"));

        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                System.out.println(filePath);
                selectedFile = Optional.of(filePath);
                updateContentString();
                loadFileContent(filePath);
            }
        });

        fileChooser.showOpenDialog(this);
    }

    private void onAddAirportClick() {
        System.out.println("Add airport");
    }

    private void onSaveClick() {

    }

    private void loadFileContent(String filePath) {
        try {
            AirportParserThread parserThread = new AirportParserThread(filePath);
            parserThread.start();
            parserThread.join();
            fileLoaded = true;
            List<Airport> airportList = parserThread.getResult();

            airports = airportList;
            updateAirportsList(airports);

            JOptionPane.showMessageDialog(this, "Successfully loaded file", "File successully loaded", JOptionPane.INFORMATION_MESSAGE);
        } catch (InterruptedException e) {
            JOptionPane pane = new JOptionPane("Error occurred while loading file", JOptionPane.ERROR_MESSAGE);
            fileLoaded = false;
            pane.setVisible(true);
        }
    }
}
