ChatApplication
===============

ChatApplication for Android and IOS with Node js  version 0.9.14


Aim 

Create a sample real time chat application for ios and android. Currently the chat application is implemented with node version 0.9.14, because the client version for socket-io obj c which is suitable for version 1.0 is not available. This repository will be updated if the socket-io obj c is updated. 


References

Socket io             :- https://github.com/Automattic/socket.io/tree/master/examples/chat

JSQMessages           :- https://github.com/jessesquires/JSQMessagesViewController

Socket-io objective c :- https://github.com/pkyeck/socket.IO-objc



Currently implemented 

Currently we have implemented a sample ios project which is a combination of socket-io objective c, JSQMessages. This will communicate with server for port 3000. We can communicate with every clients which is connected to this port.

Need to do 

1. Chatting with specific client.
2. Chatting to specific rooms.
3. Creating event for typing.
4. Creating sample project for android.
5. Need to add avatar.
