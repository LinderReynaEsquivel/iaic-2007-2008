package gui;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import micromundo.CargarEdificio;
import micromundo.EdificioCubico;
import programa.Controlador;

/**
 *
 * @author  Martinete
 */
public class Visual extends javax.swing.JFrame {
    
    private static final long serialVersionUID = 4854326049449294658L;
    
	private int n;//numero de habitaciones
    private int z;//profundidad
    private Controlador controlador;
    private String mensaje;//este string sirve para hacer print sobre el jTextArea1
    private boolean cargado;
    private ArrayList<int[]> salida;
    
    private javax.swing.JTextField edificio[][];//aqui se ver� reflejado el edificio
    private javax.swing.JTextField textoProfAct;
    private javax.swing.JTextField textoAlgoritmo;
    private javax.swing.JButton botonZoomMas;
    private javax.swing.JButton botonZoomMenos;
    private javax.swing.JButton botonAbrir;
    private javax.swing.JButton botonEjecutar;
    private javax.swing.JCheckBox checkBoxProf;
    private javax.swing.JCheckBox checkBoxAnch;
    private javax.swing.JCheckBox checkBoxAE;
    private javax.swing.JCheckBox checkBoxUni;
    private javax.swing.JCheckBox checkBoxProfIter;
    private javax.swing.JCheckBox checkBoxEscalada;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel etiquetaEstado;
    private javax.swing.JLabel etiquetaConsola;
    private javax.swing.JLabel etiquetaProf;
    private javax.swing.JLabel etiquetaAlgor;
    private javax.swing.JScrollPane scrollPaneTexto;
    private javax.swing.JTextArea textoConsola;
    
    public Visual() {
        n = 2;//dimension	
        z = 1;//profundidad del edificio
        cargado = false;
        dibuja();//pintamos la interfaz
    }
    
    public Visual(int dim){
    	n = dim;
    	z = 1;
    }
    
    public void copia(Visual v){
    	n = v.n;
        z = v.z;
        controlador = v.controlador;
        mensaje = v.mensaje;
        cargado = v.cargado;
        for(int i=0;i<n;i++){
        	for(int j=0;j<n;j++){
        		edificio[i][j]=v.edificio[i][j];
        	}
        }
        
        textoProfAct=v.textoProfAct;
        textoAlgoritmo=v.textoAlgoritmo;
        botonZoomMas=v.botonZoomMas;
        botonZoomMenos=v.botonZoomMenos;
        botonAbrir=v.botonAbrir;
        botonEjecutar=v.botonEjecutar;
        checkBoxProf=v.checkBoxProf;
        checkBoxAnch=v.checkBoxAnch;
        checkBoxAE=v.checkBoxAE;
        checkBoxUni=v.checkBoxUni;
        checkBoxProfIter=v.checkBoxProfIter;
        checkBoxEscalada=v.checkBoxEscalada;
        jDesktopPane1=v.jDesktopPane1;
        etiquetaEstado=v.etiquetaEstado;
        etiquetaConsola=v.etiquetaConsola;
        etiquetaProf=v.etiquetaProf;
        etiquetaAlgor=v.etiquetaAlgor;
        scrollPaneTexto=v.scrollPaneTexto;
        textoConsola=v.textoConsola;
    }
    
    public void rellena(ArrayList<int[]> ruta){//esta funcion colorea y rellena las habitaciones con sus respectivos numeros
        
        //Borramos las casillas 
    	for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	edificio[i][j].setText("");
            	edificio[i][j].setBackground(new java.awt.Color(255, 255, 255));
            }
    	}
    	
    	//Pintamos la casilla
    	if (ruta != null)
    		salida = ruta;
    	if (salida != null){
	    	for (int i = 0; i < salida.size(); i++) {
	            if (salida.get(i)[2]+1==z)
	            	edificio[salida.get(i)[0]][salida.get(i)[1]].setBackground(new java.awt.Color(204, 255, 204));
	    	}
	    }
    	
    	if (salida != null){
			if (salida.get(0)[2]+1==z) //si estamos en la misma profundidad pintamos 
				edificio[salida.get(0)[0]][salida.get(0)[1]].setText("X");
    		edificio[controlador.getEdificio().getIniX()][controlador.getEdificio().getIniY()].setBackground(new java.awt.Color(204, 255, 204));
        }
    	else if (salida == null && salida.get(0)[2]+1==z)
    		edificio[controlador.getEdificio().getIniX()][controlador.getEdificio().getIniY()].setText("X");
    }
    
    public boolean comprueba(){//esta funcion mira si se ha seleccionado solo una opcion
        boolean cierto=false;
        int cont=0;
        boolean[] vector=new boolean[6];
        for(int i=0;i<6;i++){//inicializamos vector
            vector[i]=false;
        } 
        vector[0]=checkBoxProf.isSelected();//cogemos los valores de los ckeck
        vector[1]=checkBoxAnch.isSelected();
        vector[2]=checkBoxAE.isSelected();
        vector[3]=checkBoxUni.isSelected();
        vector[4]=checkBoxProfIter.isSelected();
        vector[5]=checkBoxEscalada.isSelected();
      
        for(int i=0;i<6;i++){
            if(vector[i]==true){
                cont++;
            }
        }
        if(cont==1){
            cierto=true;
        }else{
            cierto=false;
        }
        return cierto;
    }
    
    public void dibuja(){//Esta funcion pinta el edificio, es decir, el numero de casillas que tenga la matriz 
    	
    	jDesktopPane1 = new javax.swing.JDesktopPane();
        botonZoomMas = new javax.swing.JButton();
        botonZoomMenos = new javax.swing.JButton();
        scrollPaneTexto = new javax.swing.JScrollPane();
        textoConsola = new javax.swing.JTextArea();
        etiquetaEstado = new javax.swing.JLabel();
        etiquetaConsola = new javax.swing.JLabel();
        textoProfAct = new javax.swing.JTextField();
        etiquetaProf = new javax.swing.JLabel();
        textoAlgoritmo = new javax.swing.JTextField();
        etiquetaAlgor = new javax.swing.JLabel();
        checkBoxProf = new javax.swing.JCheckBox();
        checkBoxAnch = new javax.swing.JCheckBox();
        checkBoxAE = new javax.swing.JCheckBox();
        checkBoxUni = new javax.swing.JCheckBox();
        checkBoxProfIter = new javax.swing.JCheckBox();
        checkBoxEscalada = new javax.swing.JCheckBox();
        botonAbrir = new javax.swing.JButton();
        botonEjecutar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 255));
        int x=20;//eje x
        int y=80;//eje y
        edificio=new JTextField[n][n];
        for (int i=0;i<n;i++) {//Creamos las habitaciones del edificio
            if(i!=0){
                y=y+30;
            }
            for(int j=0;j<n;j++){//colocamos las casillas
                edificio[i][j]=new javax.swing.JTextField();//hay que meterle el numero de habitacion y el booleano que indica si se ha pasado por ah�
                edificio[i][j].setBounds(x,y,30,30);//coloco la habitacion
                edificio[i][j].setHorizontalAlignment(JTextField.CENTER);
                jDesktopPane1.add(edificio[i][j],javax.swing.JLayeredPane.DEFAULT_LAYER);
                //actualizamos las variables de posicionamiento
                x=x+30;
                if (j==n-1){//si llegamos al final de la fila, reiniciamos la x
                    x=20;
                }
            }
        }
        //Ahora ponemos los botones zoom + y zoom -
        botonZoomMas.setText("Zoom +");
        botonZoomMas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oyenteZoomMas(evt);
            }
        });

        botonZoomMas.setBounds(edificio[n-1][n-1].getX()+50,edificio[0][n-1].getY(), 80, 23);
        jDesktopPane1.add(botonZoomMas, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonZoomMenos.setText("Zoom -");
        botonZoomMenos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oyenteZoomMenos(evt);
            }
        });

        botonZoomMenos.setBounds(edificio[n-1][n-1].getX()+50, edificio[n-1][n-1].getY()+4, 80, 23);
        jDesktopPane1.add(botonZoomMenos, javax.swing.JLayeredPane.DEFAULT_LAYER);
         
        //Titulo principal
        etiquetaEstado.setText("Estado de las Habitaciones");
        etiquetaEstado.setBounds(20, 20, 170, 14);
        jDesktopPane1.add(etiquetaEstado, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        etiquetaProf.setText("Profundidad del cubo");
        etiquetaProf.setBounds(etiquetaEstado.getX(), etiquetaEstado.getY()+30, 120, 14);
        jDesktopPane1.add(etiquetaProf, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        textoProfAct.setText(""+z);
        textoProfAct.setBounds(etiquetaProf.getX()+125,etiquetaProf.getY()-2, 20, 20);
        jDesktopPane1.add(textoProfAct, javax.swing.JLayeredPane.DEFAULT_LAYER);

        //Resto de componentes
        etiquetaAlgor.setText("Algoritmo Actual");
        etiquetaAlgor.setBounds(botonZoomMas.getX()+200, 50, 100, 14);
        jDesktopPane1.add(etiquetaAlgor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        textoAlgoritmo.setBounds(botonZoomMas.getX()+200, 70, 160, 20);
        jDesktopPane1.add(textoAlgoritmo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        
        checkBoxProf.setText("Profundidad");
        checkBoxProf.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkBoxProf.setMargin(new java.awt.Insets(0, 0, 0, 0));
        checkBoxProf.setBounds(textoAlgoritmo.getX(),textoAlgoritmo.getY()+35, 90, 15);
        jDesktopPane1.add(checkBoxProf, javax.swing.JLayeredPane.DEFAULT_LAYER);

        checkBoxAnch.setText("Anchura");
        checkBoxAnch.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkBoxAnch.setMargin(new java.awt.Insets(0, 0, 0, 0));
        checkBoxAnch.setBounds(textoAlgoritmo.getX(), checkBoxProf.getY()+20, 80, 15);
        jDesktopPane1.add(checkBoxAnch, javax.swing.JLayeredPane.DEFAULT_LAYER);

        checkBoxAE.setText("aEstrella");
        checkBoxAE.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkBoxAE.setMargin(new java.awt.Insets(0, 0, 0, 0));
        checkBoxAE.setBounds(textoAlgoritmo.getX(),checkBoxAnch.getY()+20, 80, 15);
        jDesktopPane1.add(checkBoxAE, javax.swing.JLayeredPane.DEFAULT_LAYER);

        checkBoxUni.setText("Uniforme");
        checkBoxUni.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkBoxUni.setMargin(new java.awt.Insets(0, 0, 0, 0));
        checkBoxUni.setBounds(textoAlgoritmo.getX(), checkBoxAE.getY()+20, 80, 15);
        jDesktopPane1.add(checkBoxUni, javax.swing.JLayeredPane.DEFAULT_LAYER);

        checkBoxProfIter.setText("Profundidad iterativa");
        checkBoxProfIter.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkBoxProfIter.setMargin(new java.awt.Insets(0, 0, 0, 0));
        checkBoxProfIter.setBounds(textoAlgoritmo.getX(), checkBoxUni.getY()+20, 150, 15);
        jDesktopPane1.add(checkBoxProfIter, javax.swing.JLayeredPane.DEFAULT_LAYER);

        checkBoxEscalada.setText("Escalada");
        checkBoxEscalada.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkBoxEscalada.setMargin(new java.awt.Insets(0, 0, 0, 0));
        checkBoxEscalada.setBounds(textoAlgoritmo.getX(), checkBoxProfIter.getY()+20, 80, 15);
        jDesktopPane1.add(checkBoxEscalada, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        botonAbrir.setText("Abrir");
        botonAbrir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oyenteAbrir(evt);
            }
        });
        botonAbrir.setBounds(edificio[n-1][n-1].getX(),botonZoomMenos.getY()+60, 90, 23);
        jDesktopPane1.add(botonAbrir, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonEjecutar.setText("Ejecutar");
        botonEjecutar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oyenteEjecutar(evt);
            }
        });
        botonEjecutar.setBounds(botonAbrir.getX()+100, botonZoomMenos.getY()+60, 90, 23);
        jDesktopPane1.add(botonEjecutar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        etiquetaConsola.setText("Consola");
        etiquetaConsola.setBounds(20,botonAbrir.getY()+40, 140, 20);
        jDesktopPane1.add(etiquetaConsola, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        textoConsola.setColumns(20);
        textoConsola.setRows(5);
        scrollPaneTexto.setViewportView(textoConsola);

        scrollPaneTexto.setBounds(20,etiquetaConsola.getY()+20, 570, 280);
        jDesktopPane1.add(scrollPaneTexto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );
        pack();
        this.setSize(1025,735);//ajustamos el tama�o de la ventana
        this.setVisible(true);//mostarmos la ventana
        botonEjecutar.setEnabled(cargado);
        botonZoomMas.setEnabled(cargado && controlador.getEdificio().getActZ()<n);
        botonZoomMenos.setEnabled(cargado && controlador.getEdificio().getActZ()>1);
    }
    
    /**
	 * Asocia un controlador a la vista actual para que haya una comunicacion entre ellos
	 * @param controlador que se asocia a la vista
	 */
	public void asociarControlador(Controlador cont){
		controlador = cont;
	}
	
	/**
	 * Genera una solicitud de estrategia la usuarioa para resolver cada una de las habitaciones
	 * @param b
	 * @return el nuemro de estrategia
	 */
	public int solicitud(){//te devuelve el numero de algoritmo que has seleccionado
		int juego=0;
		if(checkBoxProf.isSelected()){
			juego=1;
		}else if(checkBoxAnch.isSelected()){
			juego=2;
		}else if(checkBoxAE.isSelected()){
			juego=3;
		}else if(checkBoxUni.isSelected()){
			juego=4;
		}else if(checkBoxProfIter.isSelected()){
			juego=5;
		}else{juego=6;}
		return juego;
	}
	
	/**
	 * Muestra un mensjae por pantalla
	 * @param s mensaje que se muestra por pantalla
	 */
	public void mostrar(String s){		
		mensaje+= s;
		textoConsola.setText(mensaje);
	}
	
	/**
	 * Limpia la consola de la interfaz
	 */
	public void limpiar(){//limpiamos la consola
		textoConsola.cut();
	}
	
    private void oyenteEjecutar(java.awt.event.MouseEvent evt) {                                      
    	limpiar();
    	int estrategia=0;
        if(!comprueba()){//comprobamos si no hay error
            JOptionPane.showMessageDialog(this,"Seleccione solo una opci�n","Error",JOptionPane.ERROR_MESSAGE);
            checkBoxProf.setSelected(false);//deseleccionamos todas las opciones para que vuelva elegir
            checkBoxAnch.setSelected(false);
            checkBoxAE.setSelected(false);
            checkBoxUni.setSelected(false);
            checkBoxProfIter.setSelected(false);  
            checkBoxEscalada.setSelected(false); 
        }else{//miramos que opcion se ha seleccionado
            estrategia=solicitud();
            if (estrategia==1){
				mostrar("EMPIEZA EL JUEGO\n");
				mostrar("Busqueda en profundidad\n");
				textoAlgoritmo.setText("Busqueda en profundidad");
				controlador.jugar(1);
				
			//aplicacion ejecutar con busqueda 2
			} else if(estrategia==2){
				mostrar("EMPIEZA EL JUEGO\n");
				mostrar("Busqueda en anchura\n");
				textoAlgoritmo.setText("Busqueda en Anchura");
				controlador.jugar(2);
			
			//aplicacion ejecutar con busqueda 3
			} else if(estrategia==3){
				mostrar("EMPIEZA EL JUEGO\n");
				mostrar("Busqueda en A*\n");
				textoAlgoritmo.setText("Busqueda en A*");
				controlador.jugar(3);
			
			//aplicacion ejecutar con busqueda 4
			} else if(estrategia==4){
				mostrar("EMPIEZA EL JUEGO\n");
				mostrar("Busqueda uniforme\n");
				textoAlgoritmo.setText("Busqueda Uniforme");
				controlador.jugar(4);
				
			//aplicacion ejecutar con busqueda 5
			} else if(estrategia==5){
				mostrar("EMPIEZA EL JUEGO\n");
				mostrar("Busqueda en profundidad iterativa\n");
				textoAlgoritmo.setText("Busqueda en profunidad iterativa");
				controlador.jugar(5);
				
			//aplicacion ejecutar con busqueda 6
			} else {
				mostrar("EMPIEZA EL JUEGO\n");
				mostrar("Busqueda en escalada\n");
				textoAlgoritmo.setText("Busqueda en escalada");
				controlador.jugar(6);
			}
        }
        rellena(null);
    }                                     

    private void oyenteAbrir(java.awt.event.MouseEvent evt) {                                      
    	limpiar();
    	//Abrimos el navegador de archivos
    	JFileChooser selector = new JFileChooser();
		selector.setCurrentDirectory(new File("."));
		selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int opcion = selector.showOpenDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION){
        	File archivo = selector.getSelectedFile();
        	String ruta = archivo.getAbsolutePath();
        	CargarEdificio ce = new CargarEdificio();
        	try  {
        		EdificioCubico edi = new EdificioCubico(controlador);
        		ce.cargarEdificio(edi, ruta);
        		Visual v = new Visual(edi.getDimension());
        		v.cargado = true;
        		v.controlador = controlador;
        		controlador.asociarVista(v);
        		controlador.cargar(edi);
        		v.dibuja();
        		dispose();
        		copia(v);
        	} catch(Exception ex){
        		mostrar("Imposible abrir el archivo");
        	}
        }
        try {
        	rellena(null);
        } catch(Exception e){
        	System.out.println("Perro!!!");
        }
    	
    }     
    
    public void vaciar(){//vaciamos la ventana
    	this.setVisible(false);
    	this.removeAll();
    }
    
    private void oyenteZoomMas(java.awt.event.MouseEvent evt) {                                      
        z=z+1;
        if(z>n){
            z=n;
        }
        if (z==n)
        	botonZoomMas.setEnabled(false);
        botonZoomMenos.setEnabled(true);
        textoProfAct.setText(""+z);
        rellena(null);//actualizamos las casillas
    }                                     

    private void oyenteZoomMenos(java.awt.event.MouseEvent evt) {                                      
        z=z-1;
        if(z==0){
            z=1;
        }
        if (z==1)
        	botonZoomMenos.setEnabled(false);
        botonZoomMas.setEnabled(true);
        textoProfAct.setText(""+z);
        rellena(null);//actualizamos las casillas
    }                                     
    
    public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
    
}
