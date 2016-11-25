package agh.cs.lab3;

public class OptionParser {
	
	public MoveDirection[] parse(String[] args){
		MoveDirection[] result = new MoveDirection[args.length];
		
		for(int i=0; i<args.length; i++){
			switch(args[i]){
			case "l":
			case "left":
				result[i]=MoveDirection.Left;
				break;
			
			case "r":
			case "right":
				result[i]=MoveDirection.Right;
				break;
			
			case "b":
			case "backward":
				result[i]=MoveDirection.Backward;
				break;
			
			case "f":
			case "forward":
				result[i]=MoveDirection.Forward;
				break;
			
			default:
				throw new IllegalArgumentException(args[i] + " is not legal move specification");
			}
		}
		return result;
	}

}
