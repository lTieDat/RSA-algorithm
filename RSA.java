package RSA;
import java.math.BigInteger;
import java.util.*;
public class RSA {
    public static final int VERSION = 1024;// Length in bytes of n
    public static final BigInteger E = new BigInteger("65537");

    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phiN;
    private BigInteger d;

    public void initialize(){
        p = BigInteger.probablePrime(VERSION/2, new Random());
        q = BigInteger.probablePrime(VERSION/2, new Random());
        // use probablePrime to generate a random prime number with length = 512 bytes
        n = p.multiply(q);
        phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        d = E.modInverse(phiN);
    }
    public BigInteger encrypt(BigInteger m){
        return m.modPow(E, n);
    }
    public BigInteger decrypt(BigInteger c){
        return c.modPow(d, n);
    }
    public static void main(String[] args){
        RSA rsa = new RSA();
        rsa.initialize();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your message: ");
        String message = sc.nextLine();
        BigInteger m = new BigInteger(message.getBytes());
        BigInteger c = rsa.encrypt(m);
        System.out.println("Encrypted message: " + c);
        BigInteger decryptedMessage = rsa.decrypt(c);
        System.out.println("Decrypted message: " + new String(decryptedMessage.toByteArray()));
    }
}
