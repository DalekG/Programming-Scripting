# Port Range in Single IP
1..1024 | %{echo((new-object Net.Sockets.TcpClient).Connect("192.168.1.1",$_)) "Port $ is Open!} 2>$null

# Port List in Single IP
21,22,23,53,80,443,8080 | %{echo((net-object Net.Sockets.TcpClient).Connect("192.168.1.1", $_)) "Port $_ is Open!"} 2>$null

