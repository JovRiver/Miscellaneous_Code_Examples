/* 
 * This Program calculates the time to send packets of varying size over
 * a network at 4 kbps, starting with packets of size 4000 bits and
 * halving that size down until we hit 125 bits. It will also calculate 
 * the travel time with an additional overhead of 10 bits and then 20 bits.
 */

using System;

class Slaybaugh_Assignment1 {
    static void Main(string[] args) {
        // Initialize packetSize and overhead in bits for use with CalculateTimeToSend() method.
        int packetSize = 4000;
        int overhead = 0;

        // First pass to calculate the time to send a packet without overhead (overhead = 0).
        CalculateTimeToSend(packetSize, overhead);

        // Set overhead to 10 bits for next pass of program.
        overhead = 10;

        Console.WriteLine($"\nWith {overhead} bits of overhead:\n");
        CalculateTimeToSend(packetSize, overhead);

        // Set overhead to 20 bits for next pass of program.
        overhead = 20;
        
        Console.WriteLine($"\nWith {overhead} bits of overhead:\n");
        CalculateTimeToSend(packetSize, overhead);
    }

    // This method will calculate the time required to send packets of varying sizes
    // over a network at 4 kbps per link.
    public static void CalculateTimeToSend(int packetSize, int overhead) {

        Console.WriteLine("Size of each packet  \t  End-to-End delay\n");

         // timePerPacket: time for each packet to send over a link
         // timeToSend: time it takes for all packets to arrive at destination
        double numPackets, timePerPacket, timeToSend;

        do {
            numPackets = 16000 / packetSize;
            timePerPacket = (packetSize + overhead) / 4000.0;

            // calculate time to send entire message to destination (in seconds) and round to third decimal place
            timeToSend = Math.Round((Double)numPackets * timePerPacket + (2 * timePerPacket), 3);

            // If number of packets is less than 17, then output size of packets in kilobits otherwise in bits
            if (numPackets < 17)
                Console.WriteLine($"\t{(packetSize + overhead) / 1000.0} kb\t\t\t{timeToSend} seconds");
            else
                Console.WriteLine($"\t{packetSize + overhead} b\t\t\t{timeToSend} seconds");

            // Reduce packet sizes by 2 for next pass.
            packetSize /= 2;
        } while (numPackets < 65);
    }
}
