
 
 
 import java.util.*;
 import java.io.*;
 import java.math.*;
 
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
 	    int B;
 	    int N;
 	    if (stdin.hasNextInt()) {
 		B = stdin.nextInt();
 	    } else {
 		return ret;
 	    }
 	    if (stdin.hasNextInt()) {
 		N = stdin.nextInt();
 	    } else {
 		return ret;
 	    }
 	    int[] Mk = new int[B];
 	    for (int n1=0; n1<B; n1++) {
 		if (stdin.hasNextInt()) {
 		    Mk[n1] = stdin.nextInt();
 		} else {
 		    return ret;
 		}
 	    }
 	    
 	    solve(N,Mk);
 	}
 	ret=0;
     	return ret;
     }
 
     
     private void solve(int N_O,int[] Mk_O) {
 	BigInteger N = new BigInteger(Integer.toString(N_O-1));
 	ArrayList<BigInteger> Mk = new ArrayList<BigInteger>();
 	for (int i=0; i<Mk_O.length; i++) {
 	    Mk.add(new BigInteger(Integer.toString(Mk_O[i])));
 	}
 	BigInteger prod = new BigInteger("1");
 	BigInteger gcd = BigInteger.ZERO;
 	BigInteger lcm = null;
 	for (int i=0; i<Mk.size(); i++) {
 	    BigInteger tmp;
 	    tmp = gcd.gcd(Mk.get(i));
 	    gcd = tmp;
 	    if (lcm == null) {
 		lcm = Mk.get(i);
 	    } else {
 		tmp = lcm.multiply(Mk.get(i)).divide(gcd);
 		lcm = tmp;
 	    }
 	}
 	logInfo("gcd = %1$d",gcd);
 	logInfo("lcm = %1$d",lcm);
 	BigInteger sum1 = BigInteger.ZERO;
 	for (int i=0; i<Mk.size(); i++) {
 	    sum1 = sum1.add(lcm.divide(Mk.get(i)));
 	}
 	logInfo("sum1 = %1$d",sum1);
 	BigInteger waitTime = lcm.multiply(N.divide(sum1));
 	N = N.remainder(sum1);
 	ArrayList<Integer> barberList = new ArrayList<Integer>();
 	for (BigInteger i = BigInteger.ZERO; i.compareTo(lcm)<0 ; i = i.add(BigInteger.ONE)) {
 	    for (int j=0; j<Mk.size(); j++) {
 		if (i.remainder(Mk.get(j)).compareTo(BigInteger.ZERO)==0) {
 		    barberList.add(new Integer(j+1));
 		}
 	    }
 	}
 	output(barberList.get(Integer.parseInt(N.toString())));
     }
 
     private int output_N=0;
     
     private void output(Integer n) {
 	output_N++;
 	System.out.format("Case #%1$d: %2$d%n",output_N,n);
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
 
 class MyMath {
 
     
     public static long pow(long a,long r) {
 	long result=1;
 	long v=a;
 	for (long r1 = r; r1 > 0; ) {
 	    if (r1%2==0) {
 		v = (v*v);
 		r1/=2;
 	    } else {
 		result = (result*v);
 		r1--;
 	    }
 	}
 	return result;
     }
 
     
     public static long powMod(long a, long r, long n) {
 	long result=1;
 	long v=(a%n);
 	for (long r1 = r; r1 > 0; ) {
 	    if (r1%2==0) {
 		v = (v*v)%n;
 		r1/=2;
 	    } else {
 		result = (result*v)%n;
 		r1--;
 	    }
 	}
 	return result;
     }
 
     public static long sqrt(long n) {
 	long right=3037000499L;
 	long left=1;
 	if (n>right*right) {
 	    return -(right+1)-1;
 	}
 	if (n==0) {
 	    return 0;
 	}
 	if (n==1) {
 	    return 1;
 	}
 	if (n<0) {
 	    return -1;
 	}
 	if (n/2 < right) {
 	    right = n/2;
 	}
 	while (left<=right) {
 	    long m = (left+right)/2;
 	    long m2 = m*m;
 	    if (m2 == n) {
 		return m;
 	    }
 	    if (m2 < n) {
 		left=m+1;
 	    } else {
 		right=m-1;
 	    }
 	}
 	return -(left)-1;
     }
 
     private static long[] FACTORIAL_DP = null;
     private static int FACTORIAL_DP_MAX = 20;
     private static void factorial_init() {
 	if (FACTORIAL_DP == null) {
 	    FACTORIAL_DP = new long[FACTORIAL_DP_MAX];
 	    java.util.Arrays.fill(FACTORIAL_DP, 0);
 	    if (FACTORIAL_DP_MAX>0) {
 		FACTORIAL_DP[0]=1;
 	    }
 	}
     }
 
     
     public static long factorial(int n) {
 	long ret=1;
 	if (n<0) {
 	    return 0;
 	}
 	if (n >= FACTORIAL_DP_MAX) {
 	    for (int i=1; i<=n; i++) {
 		ret *= ((long) i);
 	    }
 	    return ret;
 	}
 	factorial_init();
 	if (FACTORIAL_DP[n] != 0) {
 	    return FACTORIAL_DP[n];
 	}
 	int i=n;
 	for ( ; i>=0; i--) {
 	    if (FACTORIAL_DP[i] != 0) {
 		break;
 	    }
 	}
 	if (i<0) {
 	    FACTORIAL_DP[0]=1;
 	    i=0;
 	}
 	for (int j=i+1; j<=n; j++) {
 	    FACTORIAL_DP[j]=FACTORIAL_DP[j-1]*((long)j);
 	}
 	return FACTORIAL_DP[n];
     }
 
     private static long[][] C_DP = null;
     private static int C_DP_MAX = 51;
     private static void C_init() {
 	if (C_DP==null) {
 	    C_DP = new long[C_DP_MAX][];
 	    for (int i=0; i<C_DP_MAX; i++) {
 		C_DP[i] = new long[i/2+1];
 		java.util.Arrays.fill(C_DP[i], 0);
 	    }
 	}
     }
 
     
     public static long C(int n, int k) {
 	long ret=0;
 	if (n<0 || k<0 || k>n) {
 	    return 0;
 	}
 	if (n==0) {
 	    return 1;
 	}
 	if (k > n/2) {
 	    k = n-k;
 	}
 	if (n >= C_DP_MAX) {
 	    ret = 1;
 	    for (int i=1; i<=k; i++) {
 		ret *= ((long)(n-i-1));
 		ret /= ((long)(i));
 	    }
 	    return ret;
 	}
 	C_init();
 	if (C_DP[n][k] != 0) {
 	    return C_DP[n][k];
 	}
 	ret = C(n-1,k) + C(n-1, k-1);
 	C_DP[n][k] = ret;
 	return ret;
     }
 
     
     public static long[] extendedGCD(long a, long b) {
 	long s=0;
         long old_s=1;
         long t=1;
         long old_t=0;
         long r=b;
         long old_r=a;
         long[] ret = new long[3];
         while (r!=0) {
             long quotient = old_r / r;
             long tmp;
             tmp = r;
             r = old_r - quotient*r;
             old_r = tmp;
             tmp = s;
             s = old_s - quotient*s;
             old_s = tmp;
             tmp = t;
             t = old_t - quotient*t;
             old_t = tmp;
         }
         ret[0] = old_r;
         ret[1] = old_s;
         ret[2] = old_t;
         return ret;
     }
 
     
     public static long pythonMOD(long a, long n) {
 	if (a>=0) {
 	    return a%n;
 	}
 	return (a%n+n)%n;
     }
 
     
     public static long[] chineseRemainderTheorem(long[] r, long[] m) {
 	long M=1;
         long ret=0;
         for (int i=0; i<m.length; i++) {
             M *= m[i];
         }
         for (int i=0; i<m.length; i++) {
             long Mi = M/m[i];
             long[] gcd = extendedGCD(Mi, m[i]);
             if (gcd[0] != 1) {
                 throw new ArithmeticException("GCD is not 1");
             }
             long inverse = pythonMOD(gcd[1], m[i]);
             
             ret += (r[i] * Mi * inverse);
             ret = pythonMOD(ret, M);
         }
 	long[] retA = new long[2];
 	retA[0] = ret;
 	retA[1] = M;
         return retA;
     }
 
     
     public static long[] chineseRemainderTheoremExtended(long[] r, long[] m) {
 	long r1 = r[0];
         long m1 = m[0];
 	long r2;
 	long m2;
         for (int i=1; i<m.length; i++) {
 	    r2 = r[i];
 	    m2 = m[i];
 	    long[] gcd = extendedGCD(m1,m2);
 	    if (r1 % gcd[0] != r2 % gcd[0]) {
                 throw new ArithmeticException("GCD is not 1 and modulo GCD doesn't match.");
             }
 	    long n = pythonMOD(r2-r1, m2);
 	    n /= gcd[0];
 	    m1 /= gcd[0];
 	    m2 /= gcd[0];
 	    long[] gcd2 = extendedGCD(m1,m2);
 	    long inv2 = pythonMOD(gcd2[1], m2);
 	    n = pythonMOD(n*inv2, m2);
 	    n *= m1*gcd[0];
 	    n += r1;
 	    m1 = m1*m2*gcd[0];
 	    r1 = pythonMOD(n, m1);
         }
 	long[] retA = new long[2];
 	retA[0] = pythonMOD(r1, m1);
 	retA[1] = m1;
         return retA;
     }
 }
 
