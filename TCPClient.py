from socket import *

serverName = "localhost"
serverPort = 6789
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName, serverPort))
sentence = input("Input a lowercase sentence: ")
clientSocket.send((sentence + "\n").encode())
modifiedSentence = clientSocket.recv(1024)
print("From Server: ", modifiedSentence.decode())
clientSocket.close()
