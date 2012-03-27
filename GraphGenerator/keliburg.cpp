// WSGraph.cpp : Defines the entry point for the console application.
//

#include <stdio.h>
#include <iostream>
#include <fstream>
#include <time.h>
#include <math.h>
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
	int N = -1;		//number of veritces
	int K = -1;		//number of inital neighbors
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
			case 'a':
				beta = atof(argv[i+1]);
				break;
			default:
				return -1;
			}
		}
		i++;
	}
	cout<<"node "<<N<<endl;
	cout<<"alpha "<<beta<<endl;
	
	int sp = (int)sqrt(N);
	
	clock_t start = clock();
	srand((unsigned)time(NULL));
	
	char file_name[100];
        sprintf(file_name, "KGR_N_%d_beta_%f", N, beta);
        ofstream fout(file_name);
	
	fout<<"Node Number"<<"\t"<<sp*sp<<endl;
	//step 1: link the K neighbors in the two sides. We only store (i,j) for i < j. 
	for(int i = 0; i < sp; i++)	
	{
		for(int j=0; j<sp;j++)
		{
			int n_index = i * sp + j;
			for(int m=i;m<sp;m++)
			{
				for(int n=0;n<sp;n++)
				{
					int m_index = m * sp + n;
					if(m_index <= n_index)
					{
						continue;
					}
					float dist = abs(i - m) + abs(j-n);
					double  p = pow(dist, -beta);
					
					float rewire_vid = (double)rand() * 1.0 / RAND_MAX;
					//cout<< p <<"\t"<<rewire_vid<<endl;
					if(rewire_vid <= p)
					{
						fout<<n_index<<"\t"<<m_index<<endl;
					}
					//else
					//{
					//	cout<<p<<"\t"<<rewire_vid<<endl;
					//}
				}
			}
		}
	}
	fout.close();
	clock_t end = clock();
	return 0;
	//fout<<i<<"\t"<<adj_list[j]<<endl;
}

