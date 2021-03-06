#include <stack>
#include <queue>
#include <string>
#include <iostream>

using namespace std;

//invert
void punto1(stack<int>* a){
  stack<int> temp;

  while(!a->empty()){
    temp.push(a->top());
    a->pop();
  }

  while(!temp.empty()){
    a->push(temp.top());
    temp.pop();
  }
}

void testPunto1(){
  stack<int> a;
  int n, e;
  cout << "Ingrese el numero de elementos" << endl;
  cin >> n;

  cout << "Ingrese los elementos" << endl;
  while(n>0){
    cin >> e;
    a.push(e);
    --n;
  }

  punto1(&a);
  cout << "OUTPUT" << endl;
  while(!a.empty()){
    cout << a.top() <<endl;
    a.pop();
  }
}

//atender
void punto2(queue<string>* q){
  while(!q->empty()){
    cout << "Atendiendo a " << q->front() <<endl;
    q->pop();
  }
}

void testPunto2(){
  queue<string> q;
  int n;
  string e;
  
  cout << "Ingrese el numero de elementos" << endl;
  cin >> n;
  
  cout << "Ingrese los elementos" << endl;
  while(n>0){
    cin >> e;
    q.push(e);
    --n;
  }
  cout << "OUTPUT" << endl;
  punto2(&q);
}

//evaluar

bool isOperand(char o){
  return o == '*' || o == '+' || o == '-' || o == '/';
}

int valueOf(char o){
  // switch(o){
  // case '1':
  //   return 1;
  // case '2':
  //   return 2;
  // case '3':
  //   return 3;
  // case '4':
  //   return 4;
  // case '5':
  //   return 5;
  // case '6':
  //   return 6;
  // case '7':
  //   return 7;
  // case '8':
  //   return 8;
  // case '9':
  //   return 9;
  // default:
  //   return 0;
  // }
  return o - '0';
}

int punto3(string expre){
  stack<int> s;
  int l, r;
  
  for(int i=0; i<expre.size(); ++i){
    if(!isOperand(expre[i]))
      s.push(valueOf(expre[i]));
      
    else{
      r = s.top();
      s.pop();
      l = s.top();
      s.pop();

      switch(expre[i]){
      case '*':
	s.push(l*r);
	break;
      case '/':
	s.push(l/r);
	break;
      case '+':
	s.push(l+r);
	break;
      case '-':
	s.push(l-r);
	break;
      }
    }
  }
  
  return s.top();
}

void testPunto3(){
  cout << "Ingrese la expresion" << endl;
  string e;
  cin >> e;

  cout << "OUTPUT" << endl;
  cout << punto3(e) << endl;
}

int main(){
  //punto1
  cout << "PUNTO1" << endl;
  testPunto1();

  //punto2
  cout << "PUNTO2" << endl;
  testPunto2();

  //punto2
  cout << "PUNTO3" << endl;
  testPunto3();
}
