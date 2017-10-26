import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

/**
 * The class Loader is the class which onliest job is to read the file which
 * containes the filestree and make a Folder with the same specifications.
 *
 * @author anietog1, kaparrah
 */
public class Loader {

    private final BufferedReader br;
    private int calls;
    private int curr;
    private int currLvl;
    private final FileStructure files;

    /**
     * Creates a new Loader which onliest job is to read a file and return a
     * Folder $HOME from it.
     *
     * @param filename The name of the file which contains the data - e.g.
     * ejemplito.txt
     * @throws FileNotFoundException if a file with filename doesn't exist.
     */
    public Loader(String filename) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(filename));
        files = new FileStructure();
        calls = 0;
    }

    /**
     *
     * @return @throws IOException
     */
    public FileStructure load() throws IOException {
        if (calls++ > 0) {
            return null;
        }

        jumpBlanks();
        while (!isLetter(curr = br.read()));
        Folder home = new Folder((char) curr + br.readLine().split("/")[0], null, "");

        jumpBlanks();
        calcLvl();
        loadTo(home, currLvl);

        return files;
    }

    private void calcLvl() throws IOException {
        if (curr == '[') {//Si el actual es '[', el nivel ya fue calculado
            return;
        }

        currLvl = 0;
        while ((curr = br.read()) != '[') {
            ++currLvl;
        }
    }

    private boolean isLetter(int curr) {
        return (curr >= 'A' && curr <= 'Z') || (curr >= 'a' && curr <= 'z');
    }

    private boolean isBlank(int curr) {
        return curr == ' ' || curr == '\n' || curr == '\t';
    }

    private boolean isDouble(int curr) {
        return (curr >= '0' && curr <= '9') || curr == '.';
    }

    private void jumpBlanks() throws IOException {
        do {
            br.mark(1);
        } while (isBlank(curr = br.read()));
        br.reset();
    }

    private void loadTo(Folder fold, int fLvl) throws IOException {
        Stack<File> inners = new Stack<>();

        while (!isDouble(curr) && curr != -1) {
            calcLvl();

            if (currLvl == fLvl) {
                inners.push(makeFor(fold));
                jumpBlanks();
            } else if (currLvl > fLvl) {
                File temp = inners.pop();
                Folder child = new Folder(temp.getName(), temp.getParent(), temp.getUser());
                loadTo(child, currLvl);
                inners.push(child);
            } else {//<
                break;
            }
        }

        while (!inners.isEmpty()) {
            files.add(inners.pop());
        }

        //files.add(fold); if uncommented, added twice :vvvv
    }

    private long calcSize(String numbers, int modifier) {
        double num = Double.parseDouble(numbers);
        long ret = (long) num;

        switch (modifier) {
            case 'M':
                ret *= 1024 * 1024;
                break;
            case 'K':
                ret *= 1024;
                break;
        }

        return ret;
    }

    private File makeFor(Folder fold) throws IOException {
        //don't need to go til '[' cuz already in there
        StringBuilder temp = new StringBuilder();
        while (!isBlank(curr = br.read())) {//getting the user
            temp.append((char) curr);
        }

        String user = temp.toString();

        jumpBlanks();

        temp = new StringBuilder();
        //next, get the size
        while (isDouble(curr = br.read())) {
            temp.append((char) curr);
        }

        long size = calcSize(temp.toString()/*digits + '.'*/, curr /*modifier*/);
        br.read();//']'
        jumpBlanks();

        return new File(br.readLine(), fold, size, user);
    }
}
