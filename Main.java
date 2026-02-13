import java.io.*;
import java.util.*;

import aco.*;
import aco.ex.*;
import sim.*;
//import graph.*;
import graph.ex.*;

/* --------------------------------- *     
*   								 *                                    
*  Project made by: Francisco Matias *
*									 *
*			 ist199936			 	 *
*           						 *
* ---------------------------------  */									

public class Main
{
	// The total number of observations while the algorithm is running.
	public static final int OBSERVATIONS = 20;

	// The maximum number of top cycles that can be printed.
	public static final int TOP_CYCLE_NUMBER = 5;

	private static int numberNodes, maxWeight, nestNode, antColonySize;
	private static double alphaMoveEvent, betaMoveEvent, deltaMoveEvent, pheromoneEvapEvtEta, pheromoneEvapEvtRo,
			pheromoneLevel, finalInstant;

	private static int[][] adjacencyMatrix = null;

	/*
	 * Runs the algorithm (Traveling salesmen problem by ant colony optimization).
	 * 
	 * @param args The input parameters.
	 */
	public static void main(String[] args)
	{
		if (args.length == 0)
		{
			System.out.println("No arguments provided.");
			System.exit(0);
		}

		String option = args[0];

		if (option.equals("-r"))
		{
			if (args.length == 12)
			{
				// The input parameters for the bind -r.
				numberNodes = Integer.parseInt(args[1]);
				maxWeight = Integer.parseInt(args[2]);
				nestNode = Integer.parseInt(args[3]);
				alphaMoveEvent = Double.parseDouble(args[4]);
				betaMoveEvent = Double.parseDouble(args[5]);
				deltaMoveEvent = Double.parseDouble(args[6]);
				pheromoneEvapEvtEta = Double.parseDouble(args[7]);
				pheromoneEvapEvtRo = Double.parseDouble(args[8]);
				pheromoneLevel = Double.parseDouble(args[9]);
				antColonySize = Integer.parseInt(args[10]);
				finalInstant = Double.parseDouble(args[11]);

				adjacencyMatrix = getRandomGraph(numberNodes, maxWeight);
			}
			else
				System.out.println("Insufficient number of arguments for the option -r.");
		}
		else if (option.equals("-f"))
		{
			if (args.length == 2)
			{
				String inputFile = args[1];
				processInputFile(inputFile);
			}
			else
				System.out.println("Input file path not provided.");
		}

		if (adjacencyMatrix == null)
		{
			System.out.println("The Adjancency Matrix is invalid.");
			System.exit(0);
		}
		
		if (nestNode > numberNodes || nestNode < 1)
		{
			System.out.println("The nest node is invalid.");
			System.exit(0);
		}

		printHelp();

		try
		{
			Simulation sim = new SimulationClass(adjacencyMatrix, nestNode, alphaMoveEvent, betaMoveEvent,
					deltaMoveEvent, pheromoneEvapEvtEta, pheromoneEvapEvtRo, pheromoneLevel, antColonySize);

//			printGraph(sim.getGraph());

			double startTime = System.currentTimeMillis();
			double endTime = startTime + (finalInstant * 1000);
			double currentTime = startTime;

			int currentInstant = 0;
			double observationOffset = finalInstant * 1000 / OBSERVATIONS;
			double[] observationInstants = new double[OBSERVATIONS];
			for (int i = 0; i < observationInstants.length; i++)
			{
				if (i == 0)
					observationInstants[i] = startTime + observationOffset;
				else
					observationInstants[i] = observationInstants[i - 1] + observationOffset;
			}

			sim.init();

			while (true)
			{
				sim.run();

				if (currentTime >= observationInstants[currentInstant])
				{
					currentInstant++;
					printOutput(sim, (int) (currentTime - startTime) / 1000, currentInstant);
				}

				currentTime = System.currentTimeMillis();
				if (currentTime >= endTime)
					break;
			}

			printOutput(sim, (int) (currentTime - startTime) / 1000, ++currentInstant);
		}
		catch (InvalidWeightException e)
		{
			System.out.println("An invalid weight value was provided to an edge!");
		}
		catch (InvalidEdgeException e)
		{
			System.out.println("An invalid edge was provided!");
		}
		catch (InvalidNodeException e)
		{
			System.out.println("An invalid node was found!");
		}
		catch (InvalidPathException e)
		{
			System.out.println("An invalid path was found!");
		}
	}

	private static void processInputFile(String inputFile)
	{
		try
		{
			File file = new File(inputFile);
			Scanner scanner = new Scanner(file);

			// Read the first line containing the input parameters
			String parametersLine = scanner.nextLine();
			String[] args = parametersLine.trim().split("\\s+");

			// Parse the parameters
			numberNodes = Integer.parseInt(args[0]);
			nestNode = Integer.parseInt(args[1]);
			alphaMoveEvent = Double.parseDouble(args[2]);
			betaMoveEvent = Double.parseDouble(args[3]);
			deltaMoveEvent = Double.parseDouble(args[4]);
			pheromoneEvapEvtEta = Double.parseDouble(args[5]);
			pheromoneEvapEvtRo = Double.parseDouble(args[6]);
			pheromoneLevel = Double.parseDouble(args[7]);
			antColonySize = Integer.parseInt(args[8]);
			finalInstant = Double.parseDouble(args[9]);

			// Initialize the adjacency matrix
			adjacencyMatrix = new int[numberNodes][numberNodes];

			// Read the remaining lines containing the adjacency matrix
			for (int i = 0; i < numberNodes; i++)
			{
				String line = scanner.nextLine();
				String[] weights = line.trim().split("\\s+");
				for (int j = 0; j < numberNodes; j++)
					adjacencyMatrix[i][j] = Integer.parseInt(weights[j]);
			}

			// Close the scanner
			scanner.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Input file not found: " + inputFile);
		}
		catch (NumberFormatException e)
		{
			System.out.println("Error parsing input file: " + e.getMessage());
		}
	}

	// Gets the random graph with the right specifications.
	private static int[][] getRandomGraph(int n, int maxWeight)
	{
		int[][] graph = new int[n][n];

		Random rand = new Random();

		for (int i = 0; i < n; i++)
		{
			for (int j = i; j < n; j++)
			{
				if (i == j)
					graph[i][j] = 0;
				else
				{
					int weight = rand.nextInt(maxWeight + 1);
					graph[i][j] = weight;
					graph[j][i] = weight;
				}
			}
		}

		return graph;
	}

	// Displays the adjacency matrix to the terminal.
	private static void displayAdjacencyMatrix(int[][] adjacencyMatrix)
	{
		int n = adjacencyMatrix.length;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (j > 0)
					System.out.print(" ");

				System.out.print(adjacencyMatrix[i][j]);
			}
			System.out.println();
		}
	}

	// Prints the Graph to the terminal.
//	private static void printGraph(Graph<Integer> graph)
//	{
//		var nodes = graph.getNodes();
//
//		while (nodes.hasNext())
//		{
//			var node = nodes.next();
//			System.out.println("Node: " + node.getId());
//
//			var edges = node.getEdges();
//
//			while (edges.hasNext())
//			{
//				var edge = edges.next();
//
//				var source = edge.getSource();
//				var destination = edge.getDestination();
//				System.out.println(
//						"\t- Edge: (" + source.getId() + ", " + destination.getId() + "), (" + edge.getWeight() + ")");
//			}
//		}
//	}

	// Prints the input parameters in order to help the user to the terminal.
	private static void printHelp()
	{
		System.out.println(numberNodes + ": number of nodes in the graph");
		System.out.println(nestNode + ": the nest node");
		System.out.println(alphaMoveEvent + ": alpha, ant move event");
		System.out.println(betaMoveEvent + ": beta, ant move event");
		System.out.println(deltaMoveEvent + ": delta, ant move event");
		System.out.println(pheromoneEvapEvtEta + ": eta, pheromone evaporation event");
		System.out.println(pheromoneEvapEvtRo + ": rho, pheromone evaporation event");
		System.out.println(pheromoneLevel + ": pheromone level");
		System.out.println(antColonySize + ": ant colony size");
		System.out.println(finalInstant + ": final instant\n");

		displayAdjacencyMatrix(adjacencyMatrix);
	}

	// Prints the cycle with the right specifications to the terminal.
	private static void printCycle(AntCycle cycle)
	{
		System.out.print("{");

		if (cycle != null)
		{
			var it = cycle.getPath();
			while (it.hasNext())
			{
				System.out.print(it.next().getId());
				if (it.hasNext())
					System.out.print(",");
			}
		}

		System.out.println("}" + (cycle != null ? ":" + cycle.getPathWeight() : ""));
	}

	// Prints the output to the terminal.
	private static void printOutput(Simulation sim, int currentInstant, int observations)
	{
		System.out.println("\nObservation " + observations + ":");
		System.out.println("Present instant: " + currentInstant);
		System.out.println("Number of move events: " + sim.getPEC().getMoveEventsNumber());
		System.out.println("Number of evaporation events: " + sim.getPEC().getEvapEventsNumber());
		System.out.println("Top candidate cycles: ");
		Iterator<AntCycle> topPaths = sim.getTopCycles(TOP_CYCLE_NUMBER);
		while (topPaths != null && topPaths.hasNext())
			printCycle(topPaths.next());
		System.out.print("Best Hamiltonian cycle : ");
		printCycle(sim.getBestCycle());
	}
}
