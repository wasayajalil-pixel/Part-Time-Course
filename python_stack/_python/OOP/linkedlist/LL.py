class Node:
    def __init__(self, value):
        self.value = value
        self.next = None

class LinkedList:
    def __init__(self):
        self.head = None

    def addAtHead(self,value):
        new_node = Node(value)
        new_node.next = self.head
        self.head = new_node 
        return self

    def addAtTail(self,value):
        new_node = Node(value)
        if self.head is None:
            self.head = new_node
        else:
            current = self.head
            while current.next:
                current = current.next
            current.next = new_node
        return self
    

    def addindex(self,val,index):
        if index == 0:
            New_node.next =self.head
            self.head = New_node
        
        New_node = Node(val)
        current = self.head
        for i in range(index-1):
            current = current.next 
        New_node.next = current.next
        current.next = New_node
        

my_list = LinkedList()
my_list.addAtHead(10).addAtHead(20).addAtHead(30)
        
    
      
