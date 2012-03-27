// WSGraph.cpp : Defines the entry point for the console application.
//

#include <stdio.h>
#include <iostream>
#include <fstream>
#include <time.h>
#define MAX_DEGREE 1000
using namespace std;


//struct Vertex
//{
//	int neighbor[1000];     //adjcent vertices
//	int degree;             //degree of this vertex
//	int rnid;		        //neighbor id that rewiring starts from
//};


int main(int argc, char** argv)
{
	int N = -1;			//number of veritces
	int K = -1;			//number of inital neighbors
	double beta = -1;	//rewiring probability


	for(int i = 1; i < argc; i++)
	{
		if(argv[i][0] == '-')
		{
			switch(argv[i][1])
			{
			case 'n':
				N = atoi(argv[i+1]);
				break;
			case 'k':
				K = atoi(argv[i+1]);
				break;
			case 'b':
				beta = atof(argv[i+1]);
				break;
			default:
				return -1;
			}
		}
		i++;
	}
	
	if(K >= 2000)
	{
		cout<<"K is too large. Assign a smaller value to it."<<endl;
		return -1;
	}

	clock_t start = clock();

	//Vertex *vertices = new Vertex [N];
	int *degree = new int [N];
	int *adj_list = new int [N * MAX_DEGREE];
	srand((unsigned)time(NULL));
	
	for(int i = 0; i < N; i++)				//step 1: link the K neighbors in the two sides. We only store (i,j) for i < j. 
	{
		degree[i] = K / 2;
		int starting_index = i * MAX_DEGREE;
		for(int j = 0; j < K / 2; j++)
		{
			adj_list[starting_index + j] = (i + j + 1) % N;
		}
	}
	//for(int i = 0; i < N; i++)
	//{
	//	cout<<i<<": ";
	//	for(int j = 0; j < degree[i]; j++)
	//		cout<<adj_list[i * MAX_DEGREE+j]<<" ";
	//	cout<<endl;
	//}


	for(int i = 0; i < N; i++)			//step 2: rewire
	{
		int starting_index = i * MAX_DEGREE;
		for(int j = starting_index; j < starting_index + degree[i]; j++)   //vertices that might be rewired
		{
			if((double)rand() / RAND_MAX < beta)
			{
				while(true)   //select a random vertex to rewire such that no self-loop or multiple edges are formed
				{
					int rewire_vid = rand() % N;   // the vertex id that will be rewired to
					if(rewire_vid == i)            //self-loop
						continue;
					if(rewire_vid > i)             //the selected vertex id is greater than i
					{
						int k;
						for(k = starting_index; k < starting_index + degree[i]; k++)
						{
							if(rewire_vid == adj_list[k])
								break;
						}
						if(k != starting_index + degree[i] && k != j)    //multiple edges
							continue;
						if(k == j)                //rewire to the selected vertex, no change is made
							break;

						adj_list[j] = rewire_vid;  //rewire
						break;
					}
					else                           //the selected vertex id is less than i
					{
						int checking_index = rewire_vid * MAX_DEGREE;
						int k;
						for(k = checking_index; k < checking_index + degree[rewire_vid]; k++)
						{
							if(adj_list[k] == i)
								break;
						}
						if(k != checking_index + degree[rewire_vid])     //the edge already exists
							continue;

						if(k >= checking_index + MAX_DEGREE)
						{
							cout<<"Assign a smaller value to K or beta."<<endl;
							return -1;
						}
						adj_list[k] = i;                                     //rewire
						degree[rewire_vid]++;


						for(k = j; k < starting_index + degree[i] - 1; k++)  //delete edge (i, j)
						{
							adj_list[k] = adj_list[k + 1];
						}
						degree[i]--;
						break;
					}

				}
			}

		}
	}

	clock_t end = clock();


	//output the graph
	char file_name[100];
	sprintf(file_name, "WSG_N_%d_K_%d_beta_%f", N, K, beta);
	ofstream fout(file_name);

	fout<<"Node Number"<<"\t"<<N<<endl;
	for(int i = 0; i < N; i++)
	{
		for(int j = i * MAX_DEGREE; j < i * MAX_DEGREE + degree[i]; j++)
		{
			fout<<i<<"\t"<<adj_list[j]<<endl;
		}
		//fout<<endl;
	}

	fout.close();

	delete [] degree;
	delete [] adj_list;


	return 0;
}

