# Algorithme Dantzig #

implementation of Danzig Algorithm in Java.

# Usage #

define you're constraints like the following exemple : 
```
 // public static int contraintes() 

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
```

> don't forgot to edit  "public static int nbxp =2;"and "public static int nbc=3;" with you're exercises 

you can also set  the coef  here 

```

public static void coefs() {

		coef[0]=1000;
		coef[1]=2000;
		coef[2]=0;
		coef[3]=0;
		coef[4]=0;
}

```

# Test without Java env #

you can copy/paste this code on [this](https://www.compilejava.net/) website and run compilation. 