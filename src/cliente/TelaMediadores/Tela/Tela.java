package cliente.TelaMediadores.Tela;



//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTree;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cliente.TelaMediadores.MediadorTela;

public class Tela extends JFrame implements InterfaceTela {

    private JPanel contentPane;
    private ImageIcon imagemForca;
    JLabel forca;
    JLabel lblStatus;
    JLabel mostrarStatus;
    JLabel palavra1;
    JLabel palavra2;
    JLabel lblLetrasErradas;
    JLabel letrasErradas;
    JLabel palpitesErrados;
    JLabel lblJogoDaForca;
    JLabel lblPalpitesErrados;
    JTabbedPane tabbedPane;
    JFormattedTextField chuteLetra;
    Label[] labelsJogadores;
    JMenuBar menuBar;
    JLabel label;
    JButton btnOk;
    JPanel panel;
    JTextField txtpnMsgJogador;
    JTextField txtpnTextoStatus;

    private MediadorTela _mediador;

    private String[] Palpite;
    private char sugestao;

    //variavel auxiliar de escolha (atravÃ©s do Jtabbed pane)
    boolean EhPalpite;

    //Ã�rea de input para palavras
    private final JTextField textField_Palavra2;
    private final JTextField textField_Palavra1;
    private JPanel table;

    /**
     * Launch the application.
     */
    /**
     * Create the frame.
     *
     * @param p1
     * @param p2
     * @param JogadoresInscritos
     * @param _mediadorT
     */
//criar labels de acordo com o nÃºmero de jogadores inscritos.String ListaLetrasErrada
    public Tela(String p1, String p2, String[] JogadoresInscritos, MediadorTela _mediadorT) {
        setResizable(false);
        this.Palpite = new String[2];
        this.sugestao = ' ';
        associarMediador(_mediadorT);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 418, 620);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        this.imagemForca = new ImageIcon("X:\\CS_JFesm\\CS_JFesm\\src\\Imagens\\HangMan0.png");
        forca = new JLabel(imagemForca);
        forca.setBorder(new LineBorder(new Color(0, 0, 0)));
        forca.setHorizontalAlignment(SwingConstants.CENTER);
        forca.setBounds(20, 59, 165, 233);
        contentPane.add(forca);

        lblStatus = new JLabel("Status:");
        lblStatus.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblStatus.setBounds(197, 59, 61, 16);
        contentPane.add(lblStatus);

        /*mostrarStatus = new JLabel("");
        mostrarStatus.setOpaque(true);
        mostrarStatus.setForeground(SystemColor.infoText);
        mostrarStatus.setBorder(new LineBorder(new Color(0, 0, 0)));
        mostrarStatus.setBackground(SystemColor.info);
        mostrarStatus.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        mostrarStatus.setBounds(196, 87, 186, 56);
        contentPane.add(mostrarStatus);
         */
        palavra1 = new JLabel();

        palavra1.setToolTipText("Essa \u00E9 a palavra 1");
        palavra1.setOpaque(true);
        palavra1.setBorder(new LineBorder(SystemColor.infoText));
        palavra1.setBackground(SystemColor.info);
        palavra1.setHorizontalAlignment(SwingConstants.CENTER);
        palavra1.setText(p1);
        palavra1.setFont(new Font("Arial", palavra1.getFont().getStyle() & ~Font.ITALIC | Font.BOLD, 24));
        palavra1.setBounds(20, 318, 362, 44);
        //palavra1.setText(p1);

        // UpdateLabel1(p1);
        contentPane.add(palavra1);

        palavra2 = new JLabel();
        palavra2.setOpaque(true);
        palavra2.setToolTipText("Essa \u00E9 a palavra 2");
        palavra2.setBorder(new LineBorder(SystemColor.infoText));
        palavra2.setBackground(SystemColor.info);
        palavra2.setHorizontalAlignment(SwingConstants.CENTER);
        palavra2.setText(p2);
        palavra2.setFont(new Font("Arial", palavra2.getFont().getStyle() & ~Font.ITALIC | Font.BOLD, 24));

        palavra2.setBounds(20, 375, 362, 44);

        contentPane.add(palavra2);

        lblLetrasErradas = new JLabel("LETRAS ERRADAS:");
        lblLetrasErradas.setBounds(20, 432, 362, 16);
        contentPane.add(lblLetrasErradas);

        // letrasErradas = new JLabel(ListaLetrasErradas);
//        letrasErradas.setBounds(160, 432, 222, 16);
        // contentPane.add(letrasErradas);
        // palpitesErrados = new JLabel(ListaLetrasErradas);
        // palpitesErrados.setBounds(147, 459, 241, 16);
        // contentPane.add(palpitesErrados);
        lblJogoDaForca = new JLabel("JOGO DA FORCA");
        lblJogoDaForca.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblJogoDaForca.setHorizontalAlignment(SwingConstants.CENTER);
        lblJogoDaForca.setHorizontalTextPosition(SwingConstants.CENTER);
        lblJogoDaForca.setBounds(20, 22, 167, 16);
        contentPane.add(lblJogoDaForca);

        lblPalpitesErrados = new JLabel("PALPITES ERRADOS:");

        lblPalpitesErrados.setBounds(20, 459, 362, 16);
        contentPane.add(lblPalpitesErrados);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(197, 162, 185, 96);
        tabbedPane.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof JTabbedPane) {
                    JTabbedPane pane = (JTabbedPane) e.getSource();
                    System.out.println("Selected paneNo : " + pane.getSelectedIndex());
                    setEhPalpite(pane.getSelectedIndex());
                }
            }
        });
        contentPane.add(tabbedPane);

        chuteLetra = new JFormattedTextField();

        chuteLetra.setHorizontalAlignment(SwingConstants.RIGHT);
        tabbedPane.addTab("Letra", null, chuteLetra, "Digite uma letra");
        chuteLetra.setFont(new Font("Arial", chuteLetra.getFont().getStyle(), 26));
        chuteLetra.setDocument(new JTextFieldLimit(1));

        table = new JPanel();
        tabbedPane.addTab("Palpite", null, table, null);

        textField_Palavra1 = new JTextField();
        textField_Palavra1.setToolTipText("Digite a primeira palavra");
        textField_Palavra1.setFont(new Font("Arial", Font.PLAIN, 18));
        table.add(textField_Palavra1);

        textField_Palavra1.setColumns(12);
        textField_Palavra1.setText("");

        textField_Palavra2 = new JTextField();
        textField_Palavra2.setToolTipText("Digite a segunda palavra");
        textField_Palavra2.setFont(new Font("Arial", Font.PLAIN, 18));
        table.add(textField_Palavra2);

        textField_Palavra2.setColumns(12);
        tabbedPane.setEnabledAt(1, true);
        textField_Palavra2.setText("");

        //cria os labels de acordo como nÃºmero de jogadores inscritos
        this.labelsJogadores = criarLabelsJogadores(JogadoresInscritos);
        for (int k = 0; k < labelsJogadores.length; k++) {
            contentPane.add(labelsJogadores[k]);
        }

        label = new JLabel("");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setEnabled(false);
        label.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        label.setBounds(10, 485, 382, 85);
        contentPane.add(label);

        btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(" o que pe" + chuteLetra.getText());
                if (EhPalpite == true) {
                    if ((!"".equals(textField_Palavra1.getText())) && (!"".equals(textField_Palavra2.getText()))) {
                        Palpite[0] = textField_Palavra1.getText().toUpperCase();

                        Palpite[1] = textField_Palavra2.getText().toUpperCase();
                        System.out.println(Palpite[0] + " " + Palpite[1]);

                        //aciona mÃ©todo de envio para o mediador
                        receberPalavras(Palpite[0], Palpite[1]);

                        //reinicializa o campo de palpites
                        textField_Palavra1.setText("");
                        textField_Palavra2.setText("");

                    } else {

                        mensagemFaltaPalavra();
                    }
                } else if (null == chuteLetra || "".equals(chuteLetra.getText()) || chuteLetra.getText() == null) {

                    System.out.println("".equals(chuteLetra.getText()));
                    mensagemFaltaLetra();
                } else {

                    sugestao = chuteLetra.getText().toUpperCase().charAt(0);

                    //aciona o mÃ©todo de envio para o mediador
                    receberLetra(sugestao);
                    //reinicializa o campo de palpites
                    chuteLetra.setText("");

                    //} else {
                }
            }

        });
        btnOk.setBounds(243, 269, 89, 23);
        contentPane.add(btnOk);

        panel = new JPanel();
        panel.setBackground(SystemColor.info);
        panel.setBounds(197, 77, 185, 71);
        contentPane.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        txtpnMsgJogador = new JTextField("");
        txtpnMsgJogador.setFont(new Font("Arial", txtpnMsgJogador.getFont().getStyle(), txtpnMsgJogador.getFont().getSize() + 4));
        txtpnMsgJogador.setEditable(false);
        txtpnMsgJogador.setHorizontalAlignment(SwingConstants.CENTER);
        txtpnMsgJogador.setBackground(SystemColor.info);
        panel.add(txtpnMsgJogador);

        txtpnTextoStatus = new JTextField();
        txtpnTextoStatus.setFont(txtpnTextoStatus.getFont().deriveFont(txtpnTextoStatus.getFont().getSize() + 4f));
        txtpnTextoStatus.setForeground(new Color(0, 0, 0));
        txtpnTextoStatus.setEditable(false);
        txtpnTextoStatus.setHorizontalAlignment(SwingConstants.CENTER);
        txtpnTextoStatus.setBackground(SystemColor.info);
        txtpnTextoStatus.setText("Você errou o palpite! ");
        panel.add(txtpnTextoStatus);
        this.setVisible(true);

    } //fim do contrutor da Tela de jogo

    //MÃ©todos de guarda para Input
    private void mensagemFaltaPalavra() {
        mostrarStatus.setText("Palpite Incompleto!");

    }

    private void mensagemFaltaLetra() {
        mostrarStatus.setText("Digite uma Letra!");

    }
    // Fim dos MÃ©todos de guarda para Input

//MÃ©todo auxiliar para captar a escolha do usuÃ¡rio de fazer palpite ou sugestÃ£o. Recebe a escolha de painel (Letra ou Palpites)
    public void setEhPalpite(int panel) {
        boolean eh;
        if (panel == 0) {
            eh = false;
        } else {
            eh = true;
        }
        this.EhPalpite = eh;
        System.out.println(eh);
    }

    //Listener de eventos para a mudanÃ§a do Jtabbed pane da tela (SignificarÃ¡ a escolha do usuÃ¡rio entre fazer uma sugestÃ£o ou palpite)
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JTabbedPane) {
            JTabbedPane pane = (JTabbedPane) e.getSource();
            System.out.println("Selected paneNo : " + pane.getSelectedIndex());
        }
    }

    /*
    InÃ­cio dos mÃ©todos ativados pelo AtualizadorTela
     */
    //mÃ©todo atualiza a imagem da forca quando necessÃ¡rio. (Ativado pelo atualizador)
    @Override
    public void atualizarImagemForca(ImageIcon FotoForca) {
        this.forca.setIcon(FotoForca);

    }

    //mÃ©todo atualiza a lista de letras usadas. (Ativador pelo atualizador)
    @Override
    public void atualizarListaLetrasUsadas(String letrasErradas) {
        this.lblLetrasErradas.setText(letrasErradas);
    }

    //mÃ©todo atualiza a tela de Status, com os dois strings. (Ativador pelo atualizador)
    @Override
    public void atualizarTextoStatus(String textoStatus, String textoJogadorAtivo) {
        this.txtpnTextoStatus.setText(textoStatus);
        this.txtpnMsgJogador.setText(textoJogadorAtivo);

    }

    //deve mudar a cor do label do Jogador
    @Override
    public void mostrarJogadorAtivo(String JogadorAtivo) {
        int i;
        String j = JogadorAtivo;
        //@wut?
        for (i = 0; i < (labelsJogadores.length); i++) {
            if (j.equals(this.labelsJogadores[i].getText())) {
                labelsJogadores[i].setBackground(Color.green);
            } else if (labelsJogadores[i].getBackground() != Color.RED) {
                labelsJogadores[i].setBackground(Color.LIGHT_GRAY);

            }
        }

    }

    public void atualizarCampoPalavras(String Palavra1, String Palavra2) {

        this.palavra1.setText(Palavra1);
        this.palavra2.setText(Palavra2);
    }

    public void desabilitaBotao() {
        this.btnOk.setEnabled(false);
    }

    public void mostrarVermelho(String nomeJogador) {
        int i;
        String j = nomeJogador;
        for (i = 0; i < (labelsJogadores.length); i++) {
            if (j.equals(this.labelsJogadores[i].getText())) {
                labelsJogadores[i].setBackground(Color.RED);
            }
        }
        
    }

    @Override
    public void atualizarPalpites(String ListaPalpitesErrados) {
        this.lblPalpitesErrados.setText(ListaPalpitesErrados);

        // setPalpitesErrados(ListaPalpites);
    }

    /*
    FIM dos mÃ©todos ativados pelo AtualizadorTela
    **/
    /**
     * InÃ­cio dos mÃ©todos de envio de mensagens para o MediadorTela
     *
     * @OK
     */
    //MÃ©todo acionado a partid do botÃ£o OK do usuÃ¡rio. Recebe a letra sugerida e envia o caractere ao Mediador
    @Override
    public void receberLetra(char letra) {
        this._mediador.recebeLetra(letra);

    }

    //MÃ©todo acionado a partid do botÃ£o OK do usuÃ¡rio. Recebe o Palpite das palavras e envia as Palavras ao Mediador 
    @Override
    public void receberPalavras(String Palavra1, String Palavra2) {

        this._mediador.recebePalpite(Palavra1, Palavra2);

    }

    /*
    FIM dos mÃ©todos de envio de mensagens para o MediadorTela
    **/

 /*
    OBS.: mÃ©todos receberNomeJogador e JogoPronto devem estar na tela de Log in, nÃ£o aqui......
     */
    @Override
    public void receberNomeJogador(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void jogoPronto(boolean jogoPronto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*
     InÃ­cio dos MÃ©todos de auxÃ­lio do Construtor
     */
    //MÃ©todo cria a quantidade de labels de acordo com o nÃºmero de jogadores inscritos
    //este mÃ©todo implementado organiza bem apenas 5 jogadores.
    //OBS. Melhoras a serem feitas: Devemos generalizar o nÃºmero de jogadores. e dividir a Ã¡rea de acordo com eles.
    private Label[] criarLabelsJogadores(String[] jogadores) {
        int i;
        Label[] LabelJogadores = new Label[jogadores.length];
        int p = 0;
        int x = 20; // deve ser generalizado.
        for (i = 0; i < jogadores.length; i++) {

            LabelJogadores[i] = new Label(jogadores[i]);
            LabelJogadores[i].setBackground(Color.LIGHT_GRAY);
            LabelJogadores[i].setFont(new Font("Arial", Font.PLAIN, 12));
            LabelJogadores[i].setBounds((x = x + p), 500, 61, 51);
            p = 77;
        }
        return LabelJogadores;
    }

    //Tela conhecerÃ¡ a intÃ¢ncia do mediador responsÃ¡vel por receber seus eventos.
    private void associarMediador(MediadorTela _mediadorT) {
        this._mediador = _mediadorT;
    }

    /*
     FIM dos MÃ©todos de auxÃ­lio do Construtor
     */
}//Fim Classe Tela
