public class AlgoDantzig {
	
    public static int nbxp =2; // Nombre de variables principales à entrer manuellement
    public static double xp[] = new double[nbxp];  // Tableau de variables principales
    
	public static int nbc=3; //Nombre de contraintes à entrer manuellement
	public static double c[][] = new double[nbc][nbxp+2]; //Tableau des contraintes
	
    public static int nbxe=nbc; // Nombre de variables d'ecart
    public static double xe[] = new double[nbxe]; // Tableau de variables d'ecart
    
    public static double totalvar[] = new double[nbxe+nbxp]; //Tableau contenant toutes les variables
    
    public static double var_contraintes[][] = new double [nbc][nbxp+nbxe+1]; //variables dans les contraintes
    
	public static double coef[] = new double[nbxp+nbxe]; //Tableau des coefs
	
	public static double equ[] = new double[nbxe+nbxp]; //Equation z
	public static double z=0; //Valeur de z
	
	public static double r[]= new double[nbc]; //Ratio
	
	public static double echange[] = new double[nbxp+nbxe+1]; //Equation d'echange
	
    public static double varech[] = new double[nbxp+1]; //Variable sortante
	 
	public static double rang_ve, rang_vs, vs, ve; //rang et valeurs vs et ve
    public static double maxVal = Integer.MAX_VALUE;
	
	//METHODES---------------------------------------------------------------------------------------
	//Methode qui permet d'indiquer les variables principales
    public static void variables_principales() {	
    	for(int i=0; i<nbxp; i++) {
    		xp[i]=0;
    	}
	}
     
 	//Methode qui permet d'indiquer les contraintes
	public static int contraintes() //à entrer manuellement
	{	
    	c[0][0]=50;
		c[0][1]=200;
		c[0][2]=1;
		c[0][3]=3000;
		c[1][0]=40;
		c[1][1]=50;
		c[1][2]=1;
		c[1][3]=1300;
		c[2][0]=10;
		c[2][1]=10;
		c[2][2]=1;
		c[2][3]=500;
		
		for (int i=0; i<nbc; i++) {

			for (int j=0; j<nbxp; j++) {
				var_contraintes[i][j] = c[i][j]; 
			}	

			for (int j=nbxp; j<nbxp+nbc; j++) {

				if (j==i+nbxp)
					var_contraintes[i][j] = c[i][nbxp];
				else 
					var_contraintes[i][j] = 0;		
			}
			var_contraintes[i][nbxp+nbc]=c[i][nbxp+1];	
		}
			
		int rang_j=0;
		for (int i=0; i<var_contraintes.length; i++) {

			if (var_contraintes[i][2]!=0)
				rang_j=i;
			else if(var_contraintes[i][3] != 0)
				rang_j=i;
			else if(var_contraintes[i][4] != 0)
            	rang_j=i;
		}

		return rang_j;
	}
	
	//Methode qui permet de determiner les variables d'ecart
	public static void variables_ecart() {	

		contraintes();

        for (int i=0; i<nbxe; i++)
        {
        	xe[i]=c[i][nbxp+1];
        }
        
	}
	
	//Methode qui regroupe les variables principales et les variables d'ecart
	public static void variables_totales() {

		variables_principales();
		variables_ecart(); 

		for (int i=0; i<nbxp; i++) {
		    totalvar[i]= xp[i];
		}

		for (int i=nbxp; i<nbxp+nbxe; i++) {
			totalvar[i]= xe[i-nbxp];
		}		
	}
	
	//Methode qui permet d'indiquer les coefs (à entrer manuellement)
	public static void coefs() {

		coef[0]=1000;
		coef[1]=2000;
		coef[2]=0;
		coef[3]=0;
		coef[4]=0;
	}
	
	//Methode qui permet de determiner l'equation z
	public static void equation_z() {

		coefs();
		double zi=z;

		for (int i=0; i<totalvar.length; i++) {
			equ[i] = coef[i]*totalvar[i];
			z= z+equ[i];	
		}

		System.out.println("z="+z);
	}
	
	//Methode qui permet de determiner la variable entrante
	public static int variable_entrante() {

		double coefmax=0;
		int ve=0;
		int rang_ve=0;
		
		for (int i = 0; i < (coef.length); i++) {
			if (coef[i] > coefmax)
			{
			coefmax = coef[i];
			rang_ve=i;
			}

		}

		return rang_ve;
	}
	
	//Methode qui permet de recuperer la valeur de la variable entrante
	public static double valeur_ve(int rang_ve) {

		int i=rang_ve;
		double ve= totalvar[rang_ve];

		return ve;
	}
	
	//Methode qui permet de determiner les ratios
	public static void ratio(int rang_ve) {

		for (int i=0; i<(r.length); i++) {
			r[i] = var_contraintes[i][5]/var_contraintes[i][rang_ve];
		}

	}
	
	//Methode qui permet de determiner le ratio minimum
	public static int ratio_minimum() {

		ratio(variable_entrante());
		//determination du ratio minimum
		double ratiomin=maxVal;
		int rang=0;
		
	    for (int i = 0; i < r.length; i++) {

	        if (r[i] < ratiomin && r[i]>0) {
	            ratiomin = r[i];
	            rang=i;
	        }
	    }

		return rang;
	}
	
	//Methode qui permet de determiner l'equation d'echange
	public static void equation_echange(int rang) {

		for (int i=0; i<nbxp+nbxe+1; i++) {
			echange[i] = var_contraintes[rang][i];
		}

	}
	
	//Methode qui permet de determiner la variable sortante
	public static int variable_sortante(double ve, int rang, int rang_ve) {

    	double var_echange[] = new double [nbxp+nbxe];
    	double val_echange[] = new double [nbxp+nbxe];

		for (int i=0; i < nbxp+nbxe; i++) {
			var_echange[i] = var_contraintes[rang][i];
			val_echange[i] = totalvar[i];
		}

		int rang_vs=0;
		   
		for (int i= 0; i<nbxp+nbxe; i++) {

			if (var_echange[i] != 0 && val_echange[i] !=0 && i != rang_ve)
				rang_vs=i;
		   }

		return rang_vs;
	}
	
	//Methode qui permet de recuperer la valeur de la variable de sortie
	public static double valeur_vs(int rang_vs) {

		double i=rang_vs;
		double vs=varech[rang_vs];

		return vs;
	}
	
	//Methode qui permet de metre a jour la valeur des variables
	public static void maj_variables(int rang_ve, int rang_vs, int rang) {

		equation_echange(ratio_minimum());

		for (int i=0; i<totalvar.length; i++) {
			if (i == rang_vs)
				totalvar[i]=0;
			else if (i==rang_ve) 
				totalvar[i]=echange[nbxp+nbxe]/var_contraintes[rang][i];
			else if (totalvar[i] == 0 && i != rang_ve)
				totalvar[i]=0;
		   }
			  		  
		for (int k=0; k<totalvar.length; k++) {
			if (k != rang_vs && k != rang_ve && !(totalvar[k] == 0 && k != rang_ve)) {

					for (int j=0; j<c.length; j++) {

					  		for(int l=0; l<nbc; l++) {

								if (var_contraintes[j][nbxp+l]!=0) {

							  		if (j != rang && var_contraintes[j][k] !=0) {
							  			totalvar[k]=(var_contraintes[j][nbxp+nbc])-(var_contraintes[j][rang_ve]*totalvar[rang_ve]);
							  		}
								}
						    }
					}
			}

			System.out.println("x"+(k+1)+" = "+totalvar[k]);
		}
	}

	//Methode qui permet de metre a jour les contraintes
	public static void maj_contraintes(int rang, int rang_ve) {

		double ech = echange[rang_ve];

		for (int i=0; i<echange.length; i++) {
			echange[i]=echange[i]/ech;
		}
		
		for (int i=0; i<echange.length; i++) {
			var_contraintes[rang][i] = echange[i];
		}
		
		for (int j=0; j<var_contraintes.length; j++) {

			double stock_var = var_contraintes[j][rang_ve];
			for(int i=0; i<echange.length; i++) {

				if (j != rang) {
					var_contraintes[j][i] = var_contraintes[j][i]-(echange[i]*stock_var);
				}
			}

		}
	}
	
	//Methode qui permet de metre a jour les coefs
	public static void maj_coef(int rang_ve) {

		double stock_var = coef[rang_ve];

		for (int i=0; i<coef.length; i++) {
			coef[i]=coef[i]-(echange[i]*stock_var);
		}
	}
	
	//Methode qui permet de metre a jour l'equation z
	public static void maj_z(int rang_ve) {

	    double zf=z+echange[nbxp+nbxe]*coef[rang_ve];
	    z=zf;
	    System.out.println("z = "+zf);

	}
	
	
    //MAIN---------------------------------------------------------------------------------------------------
	public static void main(String[] args) {

		//1ere itération
        System.out.println("1ere itération:");
        variables_totales();
        coefs();
        variable_entrante();
        contraintes();
		equation_echange(ratio_minimum());
		contraintes();
	    maj_variables(variable_entrante(),variable_sortante(valeur_ve(variable_entrante()),ratio_minimum(),variable_entrante()),ratio_minimum());
	    maj_contraintes(ratio_minimum(),variable_entrante());
	    maj_z(variable_entrante());
        maj_coef(variable_entrante ());

        //Itérations suivantes
        int iteration=2;
        for (int i=0; i<coef.length; i++) {
        	while (coef[i]>0) {
		        System.out.println();
		        System.out.println(iteration+"e itération:");
		        variable_entrante();
		        equation_echange(ratio_minimum());
		        maj_variables(variable_entrante(),variable_sortante(valeur_ve(variable_entrante()),ratio_minimum(),variable_entrante()),ratio_minimum());
		        maj_contraintes(ratio_minimum(),variable_entrante());
		        maj_z(variable_entrante());
		        maj_coef(variable_entrante ());
       			iteration++;
        	}
        }
    }
	
}