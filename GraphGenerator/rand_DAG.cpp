#include <vector>
#include <set>
#include <algorithm>
#include <iostream>
#include <fstream>
#include <time.h>
#include <string.h>
#include <sstream>

using namespace std;

struct Comp{
bool operator()(pair<int, int> a, pair<int, int> b){
if (a.first < b.first) return true;
else if (a.first > b.first) return false;
else if (a.second < b.second) return true;
else if (a.second > b.second) return false;
else return false;
}
};


int main(int argc, char** argv) {

srand ( time(NULL) );
 
vector<pair<int, int> > edgeList;
set<pair<int, int> > unique;
int node_num=atoi(argv[1]);
int degree=atoi(argv[2]);
int edge_num=node_num*degree;

for(int i=0;i<edge_num;i++)
	{
	    
		int a=rand()%node_num;
		int b=rand()%node_num;
		pair<int, int>tmp=make_pair(a,b);
		while(a==b||unique.find(tmp)!=unique.end())
		{
			a=rand()%node_num;
			b=rand()%node_num;
			tmp=make_pair(a,b);
		}
		unique.insert(tmp);
		edgeList.push_back(tmp);

	}

int k_num=node_num/1000;
stringstream ss1;
stringstream ss2;
ss1 << k_num;
ss2 << degree;
sort(edgeList.begin(), edgeList.end());
string filename="rand_"+ss1.str()+"k_"+ss2.str()+".txt";

fstream fs(filename.c_str(), fstream::out);

for ( int i = 0; i < edgeList.size(); i++ ) {
fs << edgeList[i].first << "\t" << edgeList[i].second << endl;
}
fs.close();

return 0;
}
