package graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import com.sun.javafx.geom.Edge;

public class Graph {

	private final int Max_VERTEX = 20;
	private Vertex[] VertList;
	private int Edges[][];
	private int nVerts;
	private Stack<Number> visited;

	public Graph() {
		VertList = new Vertex[Max_VERTEX];
		Edges = new int[Max_VERTEX][Max_VERTEX];
		nVerts = 0;
		visited = new Stack<Number>();

		for (int i = 0; i < Max_VERTEX; i++)
			for (int j = 0; j < Max_VERTEX; j++)
				Edges[i][j] = 0;
	}

	public void addVertex(char Label) {
		VertList[nVerts++] = new Vertex(Label);
	}

	public void addEdge(int start, int end) { // indirect graph
		Edges[start][end] = 1;
		Edges[end][start] = 1;

	}

	public void DisplayVertex(Number index) {
		System.out.println(VertList[(int) index].Label);
	}

	public int getAdjUnvisited(int v) {
		for (int i = 0; i < nVerts; i++)
			if (Edges[v][i] == 1 && VertList[i].isVisited == false)
				return i;

		return -1;
	}

	public void DFS() {
		VertList[0].isVisited = true;
		visited.push(0);
		System.out.print(VertList[0].Label);

		while (!visited.isEmpty()) {
			int v = getAdjUnvisited((int) visited.peek());

			if (v == -1)
				visited.pop();
			else {
				VertList[v].isVisited = true;
				System.out.print(VertList[v].Label);
				visited.push(v);

			}
		}

		for (int i = 0; i < nVerts; i++)
			VertList[i].isVisited = false;
	}

	public void mst() {

		VertList[0].isVisited = true;
		visited.push(0);

		while (!visited.empty()) {
			int curr = (int) visited.peek();
			int v = getAdjUnvisited(curr);

			if (v == -1)
				visited.pop();
			else {
				visited.push(v);
				VertList[v].isVisited = true;

				System.out.print(VertList[curr].Label + "" + VertList[v].Label);
				System.out.print(" ");
			}

		}
		for (int j = 0; j < nVerts; j++) // reset flags
			VertList[j].isVisited = false;
	}

	public void BFS() {
		Queue<Number> visited = new LinkedList<Number>();

		VertList[0].isVisited = true;
		visited.add(0);
		System.out.print(VertList[0].Label);
		int v2;

		while (!visited.isEmpty()) {
			int v1 = (int) visited.remove();

			while ((v2 = getAdjUnvisited(v1)) != -1) {
				VertList[v2].isVisited = true;
				System.out.print(VertList[v2].Label);
				visited.add(v2);

			}
		}

		for (int j = 0; j < nVerts; j++) // reset flags
			VertList[j].isVisited = false;

	}

	public void topo() {
		char[] sorted = new char[nVerts];
		int orig_verts = nVerts;

		while (nVerts > 0) {
			int curr = noSuccessor();
			if (curr == -1) {
				System.out.println("ERROR: Graph has cycles");
				return;
			}

			sorted[nVerts - 1] = VertList[curr].Label;
			deleteVertex(curr);
		}

		System.out.print("Topologically sorted order: ");
		for (int j = 0; j < orig_verts; j++)
			System.out.print(sorted[j]);
		System.out.println("");

	}

	public void deleteVertex(int delVert) {
		if (delVert != nVerts - 1) // if not last vertex,
		{ // delete from vertexList
			for (int j = delVert; j < nVerts - 1; j++)
				VertList[j] = VertList[j + 1];
			// delete row from adjMat
			for (int row = delVert; row < nVerts - 1; row++)
				moveRowUp(row, nVerts);
			// delete col from adjMat
			for (int col = delVert; col < nVerts - 1; col++)
				moveColLeft(col, nVerts - 1);
		}
		nVerts--; // one less vertex
	} // end deleteVertex

	private void moveRowUp(int row, int length) {
		for (int i = 0; i < length; i++) {
			Edges[row][i] = Edges[row + 1][i];
		}
	}

	private void moveColLeft(int col, int length) {
		for (int i = 0; i < length; i++) {
			Edges[i][col] = Edges[i][col + 1];
		}
	}

	public int noSuccessor() {
		// TODO Auto-generated method stub
		boolean isEdge;
		for (int i = 0; i < nVerts; i++) {
			isEdge = false;
			for (int j = 0; j < nVerts; j++) {
				if (Edges[i][j] == 1) {
					isEdge = true;
					break;
				}

			}
			if (!isEdge)
				return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		Graph theGraph = new Graph();
		theGraph.addVertex('A'); // 0 (start for mst)
		theGraph.addVertex('B'); // 1
		theGraph.addVertex('C'); // 2
		theGraph.addVertex('D'); // 3
		theGraph.addVertex('E'); // 4
		theGraph.addEdge(0, 1); // AB
		theGraph.addEdge(0, 2); // AC
		theGraph.addEdge(0, 3); // AD
		theGraph.addEdge(0, 4); // AE
		theGraph.addEdge(1, 2); // BC
		theGraph.addEdge(1, 3); // BD
		theGraph.addEdge(1, 4); // BE
		theGraph.addEdge(2, 3); // CD
		theGraph.addEdge(2, 4); // CE
		theGraph.addEdge(3, 4); // DE

		System.out.println("Visits: ");
		theGraph.DFS(); // depth-first search
		System.out.println("");
		theGraph.mst();
		System.out.println();
	}

}
