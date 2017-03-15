DTPacman:

How to use it: 	To use DTPacMan have the runGameTimed function call new DTPacman() to run a 
		decision tree for pacman. For DTPacman to work a correctly formatted dt.txt
		file must be placed in the data/AI folder.  

How it works:	When DTPacman is created it loads the nodes for the tree into an array from
		the data/AI/dt.txt file. Each node of the tree can be either and IAction or
		an ICondition. When getMove is called the tree will start at node 0 and 
		recursively run through makeDecision until an IAction is found. When found
		the IAction is returned and then ran to determine the correct MOVE for pacman.

Text file format:
		The first line must contain the number of nodes the decision tree will contain.
		Every line after will contain six fields with tabs seperating them. The order is 
		as follows:
		node number in array, condition or action number, true branch node number, false
		branch node number, variable one, and variable two

Note: 	If a node does not point to a true or false branch, or does not require any variable inputs
	a -1 can be used to designate as such. The condition and action number can be seen in the
	table below.

C/A #	Function Name		Var 1		Var 2
0	InedibleGhostWithin	distance	N/A
1	ClosestGhostIsEdible	N/A		N/A
2	GhostDistToPacman	minDist		maxDist
3	ShortestPathisSafe	N/A		N/A
10	ChaseGhost		N/A		N/A
11	FleeGhosts		N/A		N/A
12	GoNearestPill		N/A		N/A
13	GoNearestPowerPill	N/A		N/A
