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


RAP PacMan:

How to use it: To use the RAP pacman simply subsitute DTPacman, or StarterPacman from the Executor class.
    In order for our RAP Pacman to work correctly you must include a correctly formatted rap.txt file.

How it works: RAP pacman uses a series of decision sets in order to determine where pacman should go.
    Each decision has a set of goals and conditions which determine whether or not it's acted upon.
    The first test is to see whether or not that conditions goal has been met. If the goal is met that
    RAP will be removed from the queue, as it is no longer necessary (its completed its job). Next, if the
    goal is not met, the RAP's conditions are checked to see if it the game is in a state where it would
    be necessary to execute this particular RAP's action. If those conditions are met, the action is acted on
    There are two types of actions included in our implementation. One is to add a RAP's task net to the stack,
    in effect adding more RAPs with their own sets of conditions and goals to meet. The other is executing a move
    action. If a RAP's conditions are met and it has an action, it will act on that action and reset the queue.

Text File Format: Our text file is formatted in such a way as to be as readable as possible.

                    index | name | goals | conditions | tasknet | action

Notes:  Though RAPs are very potent and can certainly be powerful tools for making more engaging AI, their
    efficacy in an environment like PacMan is dubious at best. This is because RAPs are most useful when there
    is a complex system, and numerous actions to keep track of. In PacMan, the pacman character may only take
    one action at a time, and that action is entirely mutually exclusive of all others. Further, once an
    action is taken the best course is to entirely reset the RAPs queue.
