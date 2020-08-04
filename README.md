# Comparative Study of Three DNA-based information hiding methods

    With the increase of dependency on the internet for most of the current systems and applications. Some important information needs high security, so we must protect the information before sending it through the internet. The most common areas of security are cryptography, steganography, or a combination of them. Cryptography is the science of protecting information by transforming data into formats that cannot be recognized by unauthorized users. Steganography is the science of hiding information using different media such as image, audio, video, text, and DNA. The steganography based on Deoxyribonucleic acid (DNA) is a newly discovered information security technology characterized by high capacity, high randomization and low modification rate that lead to increased security. There are various methods to hide information based on DNA. the main algorithms are substitution, insertion, complementary pair. In this project, we implement three algorithms to compare them in terms of capacity, cracking property, Bit Per Nucleate (BPN) and payload. The selected methods include some cryptography algorithms which is Playfair. It also includes stenography algorithms such as the insertion method and the substitution method. We compare methods based on measures to find differences between them to help future researchers design or improve methods.


## We implement three algorithms

### Algorithm using substitution 

    In this algorithm, use the Playfair method for encryption and the substitution method for steganography

    code details on ./subsitituation method

### Algorithm using insertion

    In this algorithm, use the XOR operation for encryption and the insertion method for steganography

    code details on ./XORCipher_InsertionMethod

### Algorithm using complementary generic substitution

    In this algorithm it rely on two layers of techniques to provide more higher security. The two's techniques they followed are encryption layer by Playfair algorithm and steganography layer by complementary Substitution technique. They applied these two's layers for both ends communication sender and receiver sides when sending a message happen 

    code details on ./complementery method

## DNA database 

    we use eight different DNA reference sequence from the NCBI database to measure each of capacity, payload, and BPN. 
    all database are on ./DNA database

## contributions in this reseach 

    This project contributed to help who want to protect their information by knowing the features of each algorithm to choose the suitable for their requirements. Also, it helps the researcher to build on our study to get more secure properties. Because in our project we provide suitable information as:
    •	Measuring the basic features for the DNA-based steganography.
    •	Implementing three sample algorithms for each main steganography method.
    •	Compare the algorithms in terms of the selected features.

    If you are interested in the results, read our research

## Acknowledgment

    First and foremost, we would like to present my deepest gratitude to Almighty Allah for his bounties and blessings and for giving us the ability to finish this project.

    We would like to express our deep appreciation and our sincere gratitude to our supervisor Dr. Amjad Alamar for her valuable advice guidance throughout this project.

    Most importantly, none of this could have happened without our families and friends for continued encouragement and support during this project and along the years of study.


## Future work 

   In the future, we are thinking about extending this project by creating a new steganography method better than and stronger than others, by combining more than one algorithm, to obtain more secure data at exchanging it. Or, we can improve and overcome some of the limitations we found during our implementation of the selected algorithm. Also, we can examine the selected algorithm with a different encryption algorithm and study the effect of that on the new proposed algorithm. In addition, we are thinking about studying more steganography algorithms and make comparisons among them to clarify their advantages and their disadvantages then combine different algorithms to propose a new better algorithm that inherent the strength of each algorithm.   