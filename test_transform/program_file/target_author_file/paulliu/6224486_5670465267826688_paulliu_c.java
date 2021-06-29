
 
 
 import java.util.*;
 import java.io.*;
 
 class Main {
     private Scanner stdin = new Scanner(System.in);
     private java.util.logging.Logger logger = null;
     public static String loggerName = "MainLogger";
 
     
     private void init() {
 	
 	
     }
 
     
     private int input() {
 	int ret=0;
 	String com1;
 	int T;
 
 	if (stdin.hasNextInt()) {
 	    T = stdin.nextInt();
 	} else {
 	    return ret;
 	}
 
 	for (int t1=0; t1<T; t1++) {
 	    int L;
 	    long X;
 	    String l;
 	    if (stdin.hasNextInt()) {
 		L = stdin.nextInt();
 	    } else {
 		return ret;
 	    }
 	    if (stdin.hasNextLong()) {
 		X = stdin.nextLong();
 	    } else {
 		return ret;
 	    }
 	    if (stdin.hasNext()) {
 		l = stdin.next();
 	    } else {
 		return ret;
 	    }
 	    solve(X, l);
 	}
 
 	ret=0;
     	return ret;
     }
 
     private int multiply(char x,char y) {
 	return multiply( (int)x, (int)y );
     }
     
     private int multiply(int x,int y) {
 	int sign=1;
 	if (x<0) {
 	    sign *= -1;
 	    x=-x;
 	}
 	if (y<0) {
 	    sign *= -1;
 	    y=-y;
 	}
 	int i = (int)'i';
 	int j = (int)'j';
 	int k = (int)'k';
 	int ret=0;
 	if (x==1) {
 	    if (y==1) {
 		ret = 1;
 	    } else if (y==i) {
 		ret = i;
 	    } else if (y==j) {
 		ret = j;
 	    } else if (y==k) {
 		ret = k;
 	    } else {
 		ret = 0;
 	    }
 	} else if (x==i) {
 	    if (y==1) {
 		ret = i;
 	    } else if (y==i) {
 		ret = -1;
 	    } else if (y==j) {
 		ret = k;
 	    } else if (y==k) {
 		ret = -j;
 	    } else {
 		ret = 0;
 	    }
 	} else if (x==j) {
 	    if (y==1) {
 		ret = j;
 	    } else if (y==i) {
 		ret = -k;
 	    } else if (y==j) {
 		ret = -1;
 	    } else if (y==k) {
 		ret = i;
 	    } else {
 		ret = 0;
 	    }
 	} else if (x==k) {
 	    if (y==1) {
 		ret = k;
 	    } else if (y==i) {
 		ret = j;
 	    } else if (y==j) {
 		ret = -i;
 	    } else if (y==k) {
 		ret = -1;
 	    } else {
 		ret = 0;
 	    }
 	} else {
 	    ret = 0;
 	}
 	return sign*ret;
     }
 
     int pow_str2int(String a) {
 	int ans=1;
 	for (int i=0; i<a.length(); i++) {
 	    int c = (int)(a.charAt(i));
 	    ans = multiply(ans,c);
 	}
 	return ans;
     }
     int pow(String a,long r) {
 	int num;
 	num = pow_str2int(a);
 	int ans=1;
 	while (r>0) {
 	    if (r%2==0) {
 		r/=2;
 		num = multiply(num,num);
 	    } else {
 		r--;
 		ans = multiply(ans,num);
 	    }
 	}
 	return ans;
     }
     
     private void solve_BruteForce(long X, String l) {
 	StringBuffer sb = new StringBuffer();
 	for (long i=0; i<X; i++) {
 	    sb.append(l);
 	}
 	l = sb.toString();
 	for (int i=0; i<l.length(); i++) {
 	    int a1;
 	    a1 = pow(l.substring(0,i+1),1);
 	    if (a1 != 'i') {
 		continue;
 	    }
 	    for (int j=i+1; j<l.length(); j++) {
 		int a2;
 		int a3;
 		a2 = pow(l.substring(i+1,j),1);
 		if (a2 != 'j') {
 		    continue;
 		}
 		a3 = pow(l.substring(j),1);
 		if (a1=='i' && a2=='j' && a3=='k') {
 		    output(true);
 		    logInfo("i=%1$d, j=%2$d",i,j);
 		    return;
 		}
 	    }
 	}
 	output(false);
     }
     
     private void solve(long X, String l) {
 
 	int totalPow = pow(l,X);
 	if (totalPow != -1) {
 	    logInfo("pow(%1$s, %2$d) = %3$d",l,X,totalPow);
 	    output(false);
 	    return;
 	}
 	
 	long L = (long)(l.length());
 	int leftAns = 1;
 	long leftIndex=-1;
 	for (long i=0; i<= L*4 && i<X*L; i++) {
 	    leftAns = multiply(leftAns, (int)l.charAt((int)(i%L)));
 	    if (leftAns == (int)'i') {
 		leftIndex = i;
 		break;
 	    }
 	}
 	if (leftIndex == -1) {
 	    logInfo("leftIndex = %1$d",leftIndex);
 	    output(false);
 	    return;
 	}
 	int rightAns = 1;
 	long rightIndex=-1;
 	for (long i=0; i<= L*4 && i<X*L; i++) {
 	    long j = X*L-i-1;
 	    
 	    rightAns = multiply((int)l.charAt((int)(j%L)),rightAns);
 	    if (rightAns == (int)'k') {
 		rightIndex = j;
 		break;
 	    }
 	}
 	logInfo("leftIndex = %1$d, rightIndex = %2$d",leftIndex,rightIndex);
 	if (rightIndex == -1) {
 	    output(false);
 	    return;
 	}
 	if (leftIndex+1 > rightIndex-1) {
 	    output(false);
 	    return;
 	}
 	if ( (rightIndex-1) / L == (leftIndex+1) / L) {
 	    int jAns = pow(l.substring( (int)((leftIndex+1)%L), (int)((rightIndex-1)%L)+1 ), 1);
 	    if (jAns != (int)'j') {
 		logInfo("jAns = %1$d",jAns);
 		output(false);
 		return;
 	    }
 	} else {
 	    int jLeftAns = 1;
 	    jLeftAns = multiply(jLeftAns, pow(l.substring( (int)((leftIndex+1)%L) ), 1));
 	    int jRightAns = 1;
 	    jRightAns = multiply(pow(l.substring(0, (int)((rightIndex-1)%L)+1), 1), jRightAns);
 	    int jMidAns = 1;
 	    long jPow = (rightIndex-1)/L  - (leftIndex+1)/L - 1;
 	    logInfo("jPow = %1$d",jPow);
 	    jMidAns = pow(l, jPow);
 	    int jAns = multiply(multiply(jLeftAns, jMidAns), jRightAns);
 	    if (jAns != (int)'j') {
 		logInfo("jAns = %1$d",jAns);
 		output(false);
 		return;
 	    }
 	}
 	output(true);
     }
 
     private int output_N=0;
     
     private void output(boolean isIJK) {
 	output_N++;
 	System.out.format("Case #%1$d: %2$s%n",output_N,isIJK?"YES":"NO");
     }
 
 
     
     public void logInfo(String a, Object... args) {
 	if (logger != null) {
 	    logger.info(String.format(a,args));
 	}
     }
 
     public void begin() {
 	this.logger = java.util.logging.Logger.getLogger(Main.loggerName);
 	if (this.logger.getLevel() != java.util.logging.Level.INFO) {
 	    this.logger = null;
 	}
 	init();
 	while (input()==1) {
 	}
     }
 
     public void unittest() {
 	this.logger = java.util.logging.Logger.getLogger(Main.loggerName);
     }
 
     public static void main (String args[]) {
 	Main myMain = new Main();
 	if (args.length >= 1 && args[0].equals("unittest")) {
 	    myMain.unittest();
 	    return;
 	}
 	java.util.logging.Logger.getLogger(Main.loggerName).setLevel(java.util.logging.Level.SEVERE);
 	for (int i=0; args!=null && i<args.length; i++) {
 	    if (args[i].equals("debug")) {
 		java.util.logging.Logger.getLogger(Main.loggerName).setLevel(java.util.logging.Level.INFO);
 	    }
 	}
 	myMain.begin();
     }
 }
