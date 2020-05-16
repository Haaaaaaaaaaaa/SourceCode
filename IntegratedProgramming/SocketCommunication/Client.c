/*
Description:Socket 客户端编程主要步骤如下：
				1、socket() 创建一个 Socket
				2、connect() 与服务器连接
				3、进行会话
				4、close() 关闭 Socket
Version:	Dev-C++ 5.11
Date:		2020/05/01
*/ 

#include <stdio.h>          // Needed for printf()
#include <string.h>         // Needed for memcpy() and strcpy()
#include <windows.h>      	// Needed for all Winsock stuff
//#include<ws2tcpip.h>

//----- Defines --------------
#define  PORT_NUM     5000     		// 服务器端口号
#define  IP_ADDR  "192.168.137.1" 	// 服务器IP 地址（本机）

unsigned int         server_s;        // Server socket descriptor
struct sockaddr_in   server_addr;     // Server Internet address
char                 out_buf[100];    // 100-byte 输出缓冲区
char                 in_buf[100];     // 100-byte 接收缓冲区

/*初始化，建立Socket */
void SocketInit(){
	// 建立socket
    if((server_s=socket(AF_INET,SOCK_STREAM,0)) < 0) {
        perror("SocketError");
        exit(1);
    } else {
        printf("Client create socket successfully!\n");
    }

  	// 配置socket，建立连接
  	server_addr.sin_family      = AF_INET;    // Address family to use
  	server_addr.sin_port        = htons(PORT_NUM);   //端口号
  	server_addr.sin_addr.s_addr = inet_addr(IP_ADDR); // IP 地址
} 

/*和Server建立连接*/
void Connect(){
	//建立连接 
  	printf("请求连接...\n");
//  	connect(server_s, (struct sockaddr *)(&server_addr), sizeof(server_addr));
	if((connect(server_s, (struct sockaddr *)(&server_addr), sizeof(server_addr))) < 0) {
        perror("ConnectError");
        exit(1);
    } else {
        printf("Connnect successfully!\n");
    }
}

/*接受服务器发送来的数据*/
void ReceiveFromServer(){
	// 接收服务器的消息、
//	recv(server_s, in_buf, sizeof(in_buf), 0);
//	printf("Received from server... data = '%s' \n", in_buf);
	if(recv(server_s, in_buf, sizeof(in_buf), 0)<0){
		perror("ReceiveError");
        exit(1);
	}else{
		printf("Received from server... data = '%s' \n", in_buf);
		printf("Received successfully!\n");
	}
}

/*向服务器端发送数据*/ 
void SendToServer(){
	// 向服务器发送消息
	printf ( "Send: Hello, world!\n" ) ; 
	strcpy(out_buf, "Message -- client to server");
//	send(server_s, out_buf, (strlen(out_buf) + 1), 0);
    if ( send(server_s, out_buf, (strlen(out_buf) + 1), 0)<0) { 
        perror ( "Send error" ) ; 
        exit(1);
    } else{
    	printf("Send successfully!\n");
	}
} 
//===== Main program ======
int main(){
	WORD wVersionRequested = MAKEWORD(1,1);     // Stuff for WSA functions
  	WSADATA wsaData;           					// Stuff for WSA functions
	// 初始化 winsock
  	WSAStartup(wVersionRequested, &wsaData);
	SocketInit();
	Connect();
  	ReceiveFromServer();
  	SendToServer();
  	
  	// 关闭sockets
  	closesocket(server_s);

  	// 释放 winsock
  	WSACleanup();
  	return 0;
}

