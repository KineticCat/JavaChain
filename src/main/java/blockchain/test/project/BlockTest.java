package blockchain.test.project;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BlockTest {

    private List<Block> blockchain;
    private final int prefix = 3;
    private String prefixString;
    @Before
    public void setUp() {
        blockchain = new ArrayList<>();
        prefixString = new String(new char[prefix]).replace('\0', '0');
    }
    @Test
    public void givenBlockchain_whenNewBlockAdded_thenSuccess() {
        Block genesisBlock = new Block("Genesis Block", "0", new Date().getTime());
        blockchain.add(genesisBlock);
        Block newBlock = new Block(
                "The is a New Block.",
                blockchain.get(blockchain.size() - 1).getHash(),
                new Date().getTime());
        newBlock.mineBlock(prefix);
        assertEquals(newBlock.getHash().substring(0, prefix), prefixString);
        blockchain.add(newBlock);
    }
    @Test
    public void givenBlockchain_whenValidated_thenSuccess() {
        boolean flag = true;
        for (int i = 0; i < blockchain.size(); i++) {
            String previousHash = i ==0 ? "0" : blockchain.get(i - 1).getHash();
            flag = blockchain.get(i).getHash().equals(blockchain.get(i).calculateBlockHash())
                    && previousHash.equals(blockchain.get(i).getPreviousHash())
                    && blockchain.get(i).getHash().substring(0, prefix).equals(prefixString);
            if (!flag) break;
            System.out.println(blockchain.get(i).getHash());
        }
        assertTrue(flag);
    }
}
