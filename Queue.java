
@SuppressWarnings("unchecked")
public class Queue implements QueueInterface {
  private Node lastNode;
  
  public Queue() {
    lastNode = null;   
  }  // end default constructor
  
  // queue operations:
  public boolean isEmpty() {
        return (lastNode == null);
  }  // end isEmpty

  public void dequeueAll() {
    lastNode = null;
  }  // end dequeueAll

  public void enqueue(Object newItem) {
    // insert the new node
    if (isEmpty()) {
      // insertion into empty queue
      lastNode = new Node(newItem, null);  
      lastNode.setNext(lastNode);  
    }
    else {
      // insertion into nonempty queue
      Node newNode = new Node(newItem, lastNode.getNext());
      lastNode.setNext(newNode);
      lastNode = newNode;
    }  // end if
  }  // end enqueue

  public Object dequeue() throws QueueException {

     // INSERT YOUR CODE HERE to handle 3 cases : when queue is empty, has one item, and more than one item
     // Precondition: A queue object is created.
     // Postcondition: An object  will be removed if there is one or more than one object in the queue.
	//if the lastNode is equal to the front
	if(lastNode == lastNode.getNext()){
	Node temp = lastNode;//store the value of the lastNode which is the front
	lastNode = null; //set the lastNode to null
	return temp.getItem(); //return the front we removed  which was the lastNode in this case (the variable temp) 
	}
	
	 else  {
      Node front = lastNode.getNext(); //the front is the next node from lastNode
      Node temp = front.getNext(); //this is going to be the new front ultimately
      lastNode.setNext(temp);//we are going to set the new front from the temp variable
      return front.getItem(); //return the old front that we removed
	}

  } 
  
  public Object peek() {
    return lastNode.getNext().getItem();
  }
  public Object front() throws QueueException {
    if (!isEmpty()) {
      Node firstNode = lastNode.getNext();
      return firstNode.getItem();
    }
    else {
      throw new QueueException("QueueException on front:"
                             + "queue empty");
    }
  }

  public Object clone() throws CloneNotSupportedException
  {
	boolean copied = false;
        Queue copy = new Queue();
        Node curr = lastNode, prev = null;
        while ( (! copied) && (lastNode != null) )
        {
                Node temp = new Node(curr.getItem());
                if (prev == null)
                        copy.lastNode = temp;
                else
                        prev.setNext(temp);
                prev = temp;
                curr = curr.getNext();
		copied = (lastNode == curr);
        }
	prev.setNext(copy.lastNode);
        return copy;
  }
} // end Queue
