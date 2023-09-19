package blockchain.test.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        List<Block> blockchain = new ArrayList<>();
        int blocksToMine = 5;
        long timeStamp = new Date().getTime();
        Block genesisBlock = new Block("Genesis Data", "0", timeStamp);
        genesisBlock.mineBlock(3); // Mine the block with a prefix of 3 zeros
        blockchain.add(genesisBlock);
        String previousHash = genesisBlock.getHash();
        System.out.println("Genesis Block #" + blockchain.size() + " mined!");
        System.out.println("new Block hash:" + genesisBlock.getHash() + "| Old one: " + genesisBlock.getPreviousHash());
        while (blocksToMine > 0){
            Block newBlock = new Block("Updated data of new block",previousHash, timeStamp);
            newBlock.mineBlock(3); //Mine New Block
            blockchain.add(newBlock); // Append it To blockchain
            previousHash = newBlock.getHash();
            blocksToMine--;
            System.out.println("Block #" + blockchain.size() + " mined!");
            System.out.println("new Block hash:" + newBlock.getHash() + "| Old one: " + newBlock.getPreviousHash());
        }
    }
}