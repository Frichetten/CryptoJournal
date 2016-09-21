# CryptoJournal
CryptoJournal is an Android application designed to protect the users notes and personal journal. I chose to develop this application as a means of practicing secure coding principles. 

The first layer of protection comes from the password hashing. All passwords inputted into a user profile are stored as SHA-256, in addition they are salted and hashed several times. This is the ensure that even if the hash falls into the wrong hands, it would be difficult for the attacker to crack it. In addition, the hash will be salted and hashed hundreds more times. This will better ensure the security of the password.

(**Project on indefinite hold - working on other things**)
