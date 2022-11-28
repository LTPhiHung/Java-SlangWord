package SlangWordDictionary;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

class Main extends JPanel implements ItemListener

{
	static JFrame frame;
	private JPanel topPanel1;
	private JPanel topPanel2;
	private JPanel topPanel3;
	private JPanel topPanel4;
	private JPanel topPanel5;
	private JPanel topPanel6;
	private JPanel topPanel7;
	private JPanel topPanel8;
	private JPanel topPanel9;
	private JPanel topPanel10;


    private JPanel middlePanel1;
    private JPanel middlePanel2;
    private JPanel middlePanel3;
    private JPanel middlePanel4;
    private JPanel middlePanel5;
    private JPanel middlePanel6;
    private JPanel middlePanel8;
	private JPanel middlePanel9;
	private JPanel middlePanel10;


	private JTable table1;
	private JTable table2;
	private JTable table3;

	public JScrollPane scrollPane1;
	public JScrollPane scrollPane2;
	public JScrollPane scrollPane3;

	private String[] columnNames = new String[2];
	private String[][] dataValues;
	private String[][] historyList;
	String[] answers = new String[4];
	int userAnswer;

	int correctAnswer;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	JButton button7;
	JButton button8;
	JButton button9;
	JButton button10;


    JLabel definitionLabel1;
    JLabel definitionLabel2;
    JLabel definitionLabel3;
	JLabel definitionLabel4;
    JLabel definitionLabel5;
    JLabel definitionLabel6;
    JLabel definitionLabel8;

	JLabel slangWordLabel4;
    JLabel slangWordLabel5;
    JLabel slangWordLabel6;
    JLabel slangWordLabel8;

	JTextField definitionField1 = new JTextField();
	JTextField definitionField2 = new JTextField();
	JTextField definitionField3 = new JTextField();
	JTextField definitionField4 = new JTextField();
	JTextField definitionField5 = new JTextField();
	JTextField definitionField6 = new JTextField();
	JTextField definitionField8 = new JTextField();

	
	JTextField slangWordField4 = new JTextField();
	JTextField slangWordField5 = new JTextField();
	JTextField slangWordField6 = new JTextField();
	JTextField slangWordField8 = new JTextField();
	
	JTextField answer1;
	JTextField answer2;
	JTextField answer3;
	JTextField answer4;
	JTextField answer5;
	JTextField answer6;
	JTextField answer7;
	JTextField answer8;
	
	static JLabel labelApp;
	JPanel cards;
	final static String FEATURE1 = "Search by slang word";
	final static String FEATURE2 = "Search by slang definition";
	final static String FEATURE3 = "View search history";
	final static String FEATURE4 = "Add new slang word";
	final static String FEATURE5 = "Edit slang word";
	final static String FEATURE6 = "Delete slang word";
	final static String FEATURE7 = "Restore backup";
	final static String FEATURE8 = "On this day slang word";
	final static String FEATURE9 = "Guess the definition";
	final static String FEATURE10 = "Guess the slang word";


	SlangDictionaryApp slangWord = new SlangDictionaryApp();
	
	public Main ()

	{
		setBorder(BorderFactory.createEmptyBorder(30, //top
                30, //left
                10, //bottom
                30) //right
        );
        setLayout(new BorderLayout(5,5));
		columnNames = new String[] { "Slang word", "Definition"};

		labelApp = new JLabel("SLANG DICTIONARY APP");
		JPanel comboBoxPane = new JPanel();
		String comboBoxItems[] = { FEATURE1, FEATURE2, FEATURE3, FEATURE4, FEATURE5, FEATURE6, FEATURE7, FEATURE8,  FEATURE9,  FEATURE10 };

		JComboBox cb = new JComboBox(comboBoxItems);
		cb.setEditable(false);
		cb.addItemListener(this);
		comboBoxPane.add(cb);
		
		cards = new JPanel(new CardLayout());

		// card1
		JPanel card1 = new JPanel(new BorderLayout(20,20));

		topPanel1 = new JPanel();
		topPanel1.setLayout(new FlowLayout());
        
		definitionLabel1 = new JLabel("Enter Slang Word");

		definitionField1.setPreferredSize(new Dimension(250, 30));

        button1 = new JButton("Search");

        topPanel1.add(definitionLabel1);
        topPanel1.add(definitionField1);
        topPanel1.add(button1);
		
        card1.add(topPanel1, BorderLayout.PAGE_START);

        middlePanel1 = new JPanel();
        middlePanel1.setLayout(new FlowLayout());
		table1 = new JTable();
		scrollPane1 = new JScrollPane(table1);
		scrollPane1.setSize(150, 150);

		middlePanel1.add(scrollPane1);
        card1.add(middlePanel1, BorderLayout.CENTER);
		button1.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{	
						String word = definitionField1.getText();
						definitionField1.setText("");

						dataValues = slangWord.findDefinition(word);
						if(dataValues.length == 0) {
							JOptionPane.showMessageDialog(frame, "Slang is not in dictionary");
						}
						else {
							TableModel model = new myTableModel();
							table1.setModel(model);
						}
					}
				}
		);

	// card2
	JPanel card2 = new JPanel(new BorderLayout(20,20));

		topPanel2 = new JPanel();
		topPanel2.setLayout(new FlowLayout());
        
		definitionLabel2 = new JLabel("Enter Definition:");
		definitionField2.setPreferredSize(new Dimension(250, 30));

        button2 = new JButton("Search");

        topPanel2.add(definitionLabel2);
        topPanel2.add(definitionField2);
        topPanel2.add(button2);
		
        card2.add(topPanel2, BorderLayout.PAGE_START);
		

        middlePanel2 = new JPanel();
        middlePanel2.setLayout(new FlowLayout());
		table2 = new JTable();
		scrollPane2 = new JScrollPane(table2);
		scrollPane2.setSize(150, 150);

		middlePanel2.add(scrollPane2);
        card2.add(middlePanel2, BorderLayout.CENTER);

		button2.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
				{	
						String word = definitionField2.getText();

						dataValues = slangWord.findSlangWords(word);
						if(dataValues.length == 0) {
							JOptionPane.showMessageDialog(frame, "Definition is not in dictionary");
						} else {
							TableModel model = new myTableModel();
							table2.setModel(model);
							definitionField2.setText("");
						}
					}
				}
		);


	// card3
		JPanel card3 = new JPanel(new BorderLayout(20,20));

		topPanel3 = new JPanel();
		topPanel3.setLayout(new FlowLayout());
    
        button3 = new JButton("View History");

        topPanel3.add(button3);
		
        card3.add(topPanel3, BorderLayout.PAGE_START);
	
        middlePanel3 = new JPanel();
        middlePanel3.setLayout(new FlowLayout());
		table3 = new JTable();
		scrollPane3 = new JScrollPane(table3);
		scrollPane3.setSize(150, 150);

		middlePanel3.add(scrollPane3);
        card3.add(middlePanel3, BorderLayout.CENTER);
		button3.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
				{
					dataValues = slangWord.ViewSearchHistory();
					if(dataValues.length == 0) {
						JOptionPane.showMessageDialog(frame, "No search history");
					} else {
						TableModel model = new myTableModel();
						table3.setModel(model);
						definitionField3.setText("");
					}
					}
				}
		);

		// card4
		JPanel card4 = new JPanel(new BorderLayout(20,20));

		topPanel4 = new JPanel();
		topPanel4.setLayout(new GridLayout(0,2, 10, 10));

		slangWordLabel4 = new JLabel("Enter Slang Word");
		topPanel4.add(slangWordLabel4);
		topPanel4.add(slangWordField4);
		slangWordField4.setPreferredSize(new Dimension(250, 30));

		middlePanel4 = new JPanel(new FlowLayout());
		// slangWordLabel4.setPreferredSize(new Dimension(250,30));
		slangWordLabel4.setHorizontalAlignment(JLabel.RIGHT);

		definitionLabel4 = new JLabel("Enter Definition ");
		topPanel4.add(definitionLabel4);
		topPanel4.add(definitionField4);
		definitionField4.setPreferredSize(new Dimension(250, 30));
		definitionLabel4.setHorizontalAlignment(JLabel.RIGHT);
		
		button4 = new JButton("Add");
		middlePanel4.add(button4);

		card4.add(topPanel4, BorderLayout.PAGE_START);
		card4.add(middlePanel4, BorderLayout.LINE_END);
		button4.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event)
					{
					String word = slangWordField4.getText();
					String definition = definitionField4.getText();
					slangWordField4.setText("");
					definitionField4.setText("");
					
					if(slangWord.checkSlangWordExist(word)) {
						Object[] options = {"Overwrite","Duplicate","Cancel"};
						int type = JOptionPane.showOptionDialog(frame, "Slang word is exist, Choose type of edit:", "Add new slang word", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
					
						if(type == 0) {
							slangWord.AddNewWord(0, word, definition);
							System.out.print("0");
						}
						else if(type == 1) {
							slangWord.AddNewWord(1, word, definition);
							System.out.print("1");
						}
					}
					else {
						slangWord.add(word, definition);
						slangWord.saveFile("slang.txt");
					}
				}
			}
		);

		// card5
		JPanel card5 = new JPanel(new BorderLayout(20,20));

		topPanel5 = new JPanel();
		topPanel5.setLayout(new GridLayout(0,2, 10, 10));

		slangWordLabel5 = new JLabel("Enter Slang Word");
		topPanel5.add(slangWordLabel5);
		topPanel5.add(slangWordField5);
		slangWordField5.setPreferredSize(new Dimension(250, 30));
		slangWordLabel5.setHorizontalAlignment(JLabel.RIGHT);

	

		definitionLabel5 = new JLabel("Enter Definition  ");
		topPanel5.add(definitionLabel5);
		topPanel5.add(definitionField5);
		definitionField5.setPreferredSize(new Dimension(250, 30));
		definitionLabel5.setHorizontalAlignment(JLabel.RIGHT);
		
	
		middlePanel5 = new JPanel(new FlowLayout());
		button5 = new JButton("Edit");
		middlePanel5.add(button5);

		card5.add(topPanel5, BorderLayout.PAGE_START);
		card5.add(middlePanel5, BorderLayout.LINE_END);
		button5.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event)
					{
						String word = slangWordField5.getText();
						String definition = definitionField5.getText();
						slangWordField6.setText("");
						definitionField6.setText("");
						
						if(slangWord.checkSlangWordExist(word)) {
							slangWord.editSlangWord(word, definition);
							JOptionPane.showMessageDialog(frame, "Edit slang word successful!");
						}
						else {
							JOptionPane.showMessageDialog(frame, "Slang word is not in dictionary");
						}
					}
			}
		);

		// card6
		JPanel card6 = new JPanel(new BorderLayout(20,20));

		topPanel6 = new JPanel();
		topPanel6.setLayout(new GridLayout(0,2, 10, 10));

		slangWordLabel6 = new JLabel("Enter Slang Word");
		topPanel6.add(slangWordLabel6);
		topPanel6.add(slangWordField6);
		slangWordField6.setPreferredSize(new Dimension(250, 30));
		slangWordLabel6.setHorizontalAlignment(JLabel.RIGHT);

		definitionLabel6 = new JLabel("Enter Definition");
		topPanel6.add(definitionLabel6);
		topPanel6.add(definitionField6);
		definitionField6.setPreferredSize(new Dimension(250, 30));
		definitionLabel6.setHorizontalAlignment(JLabel.RIGHT);

		middlePanel6 = new JPanel(new FlowLayout());
		button6 = new JButton("Delete");
		middlePanel6.add(button6);
		card6.add(topPanel6, BorderLayout.PAGE_START);
		card6.add(middlePanel6, BorderLayout.LINE_END);
		
		button6.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event)
					{
						String word = slangWordField6.getText();
						slangWordField6.setText("");
						boolean exist = slangWord.checkSlangWordExist(word); 
						if(exist) {
							if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete slang word?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
								slangWord.deleteSlangWord(true, word);
							} else {
								slangWord.deleteSlangWord(false, word);
							}
						}
						else {
							JOptionPane.showMessageDialog(frame, "Slang is not in dictionary");
						}
						definitionField6.setText("");
					}
			}
	);

		// card7
		JPanel card7 = new JPanel(new BorderLayout(20,20));

		topPanel7 = new JPanel();
		topPanel7.setLayout(new FlowLayout());
		JLabel label7 = new JLabel("Reset the original slang words list");
		button7 = new JButton("Reset");
		label7.setHorizontalAlignment(JLabel.CENTER);
		
		topPanel7.add(button7);

		card7.add(label7, BorderLayout.PAGE_START);
		card7.add(topPanel7, BorderLayout.CENTER);

		button7.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event)
						{
							if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to restore backup?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
								slangWord.RestoreBackup(true);
							} else {
								slangWord.RestoreBackup(false);
							}
						}
				}
		);

		
		// card8
		JPanel card8 = new JPanel(new BorderLayout(20,20));

		topPanel8 = new JPanel();
		topPanel8.setLayout(new GridLayout(0,2, 10, 10));

		slangWordLabel8 = new JLabel("Slang Word");
		topPanel8.add(slangWordLabel8);
		topPanel8.add(slangWordField8);
		slangWordField8.setPreferredSize(new Dimension(250, 30));
		slangWordField8.setEditable(false);

		definitionLabel8 = new JLabel("Definition");
		topPanel8.add(definitionLabel8);
		topPanel8.add(definitionField8);
		definitionField8.setPreferredSize(new Dimension(250, 30));
		definitionField8.setEditable(false);

		definitionLabel8.setHorizontalAlignment(JLabel.RIGHT);
		slangWordLabel8.setHorizontalAlignment(JLabel.RIGHT);


		middlePanel8 = new JPanel(new FlowLayout());
		button8 = new JButton("Random");
		middlePanel8.add(button8);
		card8.add(topPanel8, BorderLayout.PAGE_START);
		card8.add(middlePanel8, BorderLayout.CENTER);
		
		button8.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
				{
						String[] randomWord = slangWord.RandomWord();
						slangWordField8.setText(randomWord[0]);
						definitionField8.setText(randomWord[1]);

					}
				}
		);

		// card 9
		JPanel card9 = new JPanel(new BorderLayout(10,20));

		topPanel9 = new JPanel();
		topPanel9.setLayout(new GridLayout(0,2, 10, 10));

		final JLabel question = new JLabel("Guess the definition of: ");

		JLabel answerNumber1 = new JLabel("A. ");
		JLabel answerNumber2 = new JLabel("B. ");
		JLabel answerNumber3 = new JLabel("C. ");
		JLabel answerNumber4 = new JLabel("D. ");

		answer1 = new JTextField("");
		answer2 = new JTextField("");
		answer3 = new JTextField("");
		answer4 = new JTextField("");

		button9 = new JButton("Random");

        topPanel9.add(Box.createRigidArea(new Dimension(5, 0)));
		topPanel9.add(question);

		topPanel9.add(answerNumber1);
		topPanel9.add(answer1);
		topPanel9.add(answerNumber2);
		topPanel9.add(answer2);
		topPanel9.add(answerNumber3);
		topPanel9.add(answer3);
		topPanel9.add(answerNumber4);
		topPanel9.add(answer4);

		answerNumber1.setHorizontalAlignment(JLabel.RIGHT);
		answerNumber2.setHorizontalAlignment(JLabel.RIGHT);
		answerNumber3.setHorizontalAlignment(JLabel.RIGHT);
		answerNumber4.setHorizontalAlignment(JLabel.RIGHT);


		answer1.setPreferredSize(new Dimension(250, 30));
		answer1.setEditable(false);
		answer2.setPreferredSize(new Dimension(250, 30));
		answer2.setEditable(false);
		answer3.setPreferredSize(new Dimension(250, 30));
		answer3.setEditable(false);
		answer4.setPreferredSize(new Dimension(250, 30));
		answer4.setEditable(false);


		middlePanel9 = new JPanel(new FlowLayout());
		
		JLabel answerLabel = new JLabel("Your answer");

		String[] answerList = {"A", "B", "C", "D"};
		final JComboBox comboBox1=new JComboBox(answerList); 

		middlePanel9.add(answerLabel);
		middlePanel9.add(comboBox1);
		middlePanel9.add(button9);
		
		comboBox1.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {       
				userAnswer = comboBox1.getSelectedIndex();
			}  
		});     

		JButton answerBtn1 = new JButton("Answer");
		middlePanel9.add(answerBtn1);
		card9.add(topPanel9, BorderLayout.PAGE_START);
		card9.add(middlePanel9, BorderLayout.CENTER);
		
		button9.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						answers = slangWord.GuessDefinition();
						answer1.setText(answers[0]);
						answer2.setText(answers[1]);
						answer3.setText(answers[2]);
						answer4.setText(answers[3]);

						String questionLabel = slangWord.getQuestion();
						question.setText("Guess the definition of: " + questionLabel);
					}
				}
		);
		
		answerBtn1.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					correctAnswer = slangWord.getCorrectAnswer();
					if(userAnswer == correctAnswer) {
						JOptionPane.showMessageDialog(frame, "Correct Answer!");
					} else {
						JOptionPane.showMessageDialog(frame, "You haven't answered yet! , the correct answer is:  " + answers[correctAnswer]);
					}
					answer1.setText("");
					answer2.setText("");
					answer3.setText("");
					answer4.setText("");
					comboBox1.setSelectedIndex(0);
					question.setText("Guess the definition of: ");
				}
			}
	);
		// card 10
		JPanel card10 = new JPanel(new BorderLayout(10,20));

		topPanel10 = new JPanel();
		topPanel10.setLayout(new GridLayout(0,2, 10, 10));

		final JLabel question2 = new JLabel("Guess the definition of: ");

		JLabel answerNumber5 = new JLabel("A. ");
		JLabel answerNumber6 = new JLabel("B. ");
		JLabel answerNumber7 = new JLabel("C. ");
		JLabel answerNumber8 = new JLabel("D. ");

		answer5 = new JTextField("");
		answer6 = new JTextField("");
		answer7 = new JTextField("");
		answer8 = new JTextField("");

		button10 = new JButton("Random");
        topPanel10.add(Box.createRigidArea(new Dimension(5, 0)));
		topPanel10.add(question2);

		topPanel10.add(answerNumber5);
		topPanel10.add(answer5);
		topPanel10.add(answerNumber6);
		topPanel10.add(answer6);
		topPanel10.add(answerNumber7);
		topPanel10.add(answer7);
		topPanel10.add(answerNumber8);
		topPanel10.add(answer8);

		answerNumber5.setHorizontalAlignment(JLabel.RIGHT);
		answerNumber6.setHorizontalAlignment(JLabel.RIGHT);
		answerNumber7.setHorizontalAlignment(JLabel.RIGHT);
		answerNumber8.setHorizontalAlignment(JLabel.RIGHT);


		answer5.setPreferredSize(new Dimension(250, 30));
		answer5.setEditable(false);
		answer6.setPreferredSize(new Dimension(250, 30));
		answer6.setEditable(false);
		answer7.setPreferredSize(new Dimension(250, 30));
		answer7.setEditable(false);
		answer8.setPreferredSize(new Dimension(250, 30));
		answer8.setEditable(false);


		middlePanel10 = new JPanel(new FlowLayout());
		JLabel answerLabel2 = new JLabel("Your answer");

		// String[] answerList = {"A", "B", "C", "D"};
		final JComboBox comboBox2 =new JComboBox(answerList); 
		
		middlePanel10.add(answerLabel2);
		middlePanel10.add(comboBox2);
		middlePanel10.add(button10);

		comboBox1.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {       
				userAnswer = comboBox1.getSelectedIndex();
			}  
		}); 

		JButton answerBtn2 = new JButton("Answer");
		middlePanel10.add(answerBtn2);
		card10.add(topPanel10, BorderLayout.PAGE_START);
		card10.add(middlePanel10, BorderLayout.LINE_END);
		// card10.add(centerPanel, BorderLayout.CENTER);
		button10.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						answers = slangWord.GuessSlangWord();
						answer5.setText(answers[0]);
						answer6.setText(answers[1]);
						answer7.setText(answers[2]);
						answer8.setText(answers[3]);

						String questionLabel = slangWord.getQuestion();
						question2.setText("Guess the slang word of: " + questionLabel);
					}
				}
		);
		
		answerBtn2.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					correctAnswer = slangWord.getCorrectAnswer();
					if(userAnswer == correctAnswer) {
						JOptionPane.showMessageDialog(frame, "Correct Answer!");
					} else {
						JOptionPane.showMessageDialog(frame, "You haven't answered yet! , the correct answer is:  " + answers[correctAnswer]);
					}
					answer5.setText("");
					answer6.setText("");
					answer7.setText("");
					answer8.setText("");
					comboBox2.setSelectedIndex(0);
					question2.setText("Guess the definition of:  ");
				}
			}
	);

		cards.add(card1, FEATURE1);
		cards.add(card2, FEATURE2);
		cards.add(card3, FEATURE3);
		cards.add(card4, FEATURE4);
		cards.add(card5, FEATURE5);
		cards.add(card6, FEATURE6);
		cards.add(card7, FEATURE7);
		cards.add(card8, FEATURE8);
		cards.add(card9, FEATURE9);
		cards.add(card10, FEATURE10);


		add(labelApp, BorderLayout.PAGE_START);
		add(comboBoxPane, BorderLayout.LINE_START);
		add(cards, BorderLayout.CENTER);
	}

	public static void changeFont ( Component component, Font font )
	{
		component.setFont ( font );
		if ( component instanceof Container )
		{
			for ( Component child : ( ( Container ) component ).getComponents () )
			{
				changeFont ( child, font );
			}
		}
	}


	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout)(cards.getLayout());
		cl.show(cards, (String)evt.getItem());
	}

	public class myTableModel extends DefaultTableModel
	{
		myTableModel()

		{

			super(dataValues, columnNames);
		}

		public boolean isCellEditable(int row, int cols)

		{
			return true;
		}

	}


    public static void createAndShowGUI() {
		frame = new JFrame("demo1");
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		Main newContentPane = new Main();
		frame.setContentPane(newContentPane);
		changeFont(newContentPane, new Font("Serif", Font.PLAIN, 15));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		labelApp.setFont(new Font("Serif", Font.PLAIN, 30));

		// frame.setSize(1000,1000);
		frame.setVisible(true);
		frame.pack();
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
    }
}