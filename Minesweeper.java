import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Minesweeper extends JFrame{
    
    private JMenuBar menuBar;
    private JMenu menu1;
    private JMenuItem menuItem1, menuItem2;
    
    private JPanel northPanel;
    private JButton btnSmiley;
    
    private JPanel centerPanel;
    private JButton [][] btnMatrix = new JButton[10][10];
    
    private Icon smileyIcon;
    private Icon mineIcon;
    
    public Minesweeper(){
        super("Minesweeper Game");
        createResources();
        initMenu();
        initMenuItems();
        initPanels();
        initIcons();
        assambleMenu();
        assambleNorthPanel();
        assambleCenterPanel();
        createButtons();
        
        Game.getInstance().startGame();

        configureFrame();
    }

    private void createResources(){
        smileyIcon = new ImageIcon("s.gif");
        mineIcon = new ImageIcon("mine.jpg");
    }
    
    private void initIcons(){
        btnSmiley = new JButton(smileyIcon);
    }
    
    private void initMenu(){
        menuBar = new JMenuBar();
        menu1 = new JMenu("Options");
    }
    
    private void initMenuItems(){
        menuItem1 = new JMenuItem("New");
        menuItem2 = new JMenuItem("Exit");
    }
    
    private void initPanels(){
        northPanel = new JPanel();
        centerPanel = new JPanel();
    }
    
    private void assambleMenu(){
        setJMenuBar(menuBar);
        menuBar.add(menu1);
        menu1.add(menuItem1);
        menu1.add(menuItem2);
        
        menuItem1.addActionListener(event -> newGame());
        menuItem2.addActionListener(event -> System.exit(0));
    }
    
    private void assambleNorthPanel(){
        northPanel.add(btnSmiley);
        btnSmiley.addActionListener(event -> newGame());
        add(northPanel, BorderLayout.NORTH);
    }
    
    private void assambleCenterPanel(){
        centerPanel.setLayout(new GridLayout(10, 10));
        add(centerPanel);
    }
    
    private void createButtons(){
        for(int i = 0; i< btnMatrix.length; i++){
            for(int j = 0; j < btnMatrix[i].length; j++){
                btnMatrix[i][j] = new JButton();
                btnMatrix[i][j].addActionListener(this::btnPressed);
                centerPanel.add(btnMatrix[i][j]);
            }
        }
    }
    
    private void configureFrame(){
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    private void enableAllButtons(boolean enable){
        for(int i = 0; i < btnMatrix.length; i++){
            for(int j = 0; j < btnMatrix[i].length; j++){
                btnMatrix[i][j].setEnabled(enable);
            }
        }
    }
    
    private void clearAllButtons(){
        for(int i = 0; i < btnMatrix.length; i++){
            for(int j = 0; j < btnMatrix[i].length; j++){
                btnMatrix[i][j].setIcon(null);
                btnMatrix[i][j].setText(null);
            }
        }
    }
    
    private void showMineOnButtons(){
        for(int i = 0; i < btnMatrix.length; i++){
            for(int j = 0; j < btnMatrix[i].length; j++){
                int value = Game.getInstance().getCellValue(i, j);
                if (value < 0){
                    btnMatrix[i][j].setIcon(mineIcon);
                }
            }
        }
    }
    
    private void gameOver(){
        enableAllButtons(false);
        showMineOnButtons();
        JOptionPane.showMessageDialog(this, "Ooops! Game Over!");
    }
    
    private void newGame(){
        enableAllButtons(true);
        clearAllButtons();
        Game.getInstance().startGame();       
    }
    
    private void btnPressed(ActionEvent event){
        for(int i = 0; i< btnMatrix.length; i++){
            for(int j = 0; j < btnMatrix[i].length; j++){
                if(event.getSource() == btnMatrix[i][j]){
                    int value = Game.getInstance().getCellValue(i, j);
                    
                    if (value < 0){
                        gameOver();
                    } else {
                        btnMatrix[i][j].setText(String.valueOf(value));
                        btnMatrix[i][j].setEnabled(false);
                    }
                }
            }
        }
    }
}