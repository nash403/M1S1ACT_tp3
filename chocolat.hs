type Chocolate = (Int, Int, Int, Int)

{-displayPlaque:: Chocolat -> IO()
displayPlaque (1,1,0,0)   = putStr "|X"
displayPlaque (x,y,mx,my) = -}

{-valuePositionChocolate:: Chocolat -> Int
valuePositionChocolate (1,1,0,0) = 0
valuePositionChocolate (m,n,k,l) = let coupl= fonc 1 ([],[]) ( 
				   where fonc dir (ps,ns) (a,b,c,d) =
				   	 case dir of 1: valuePositionChocolate(a,b,c,d)
					      	     2:
						     3:
						     4:-}

value:: [Int] -> Int -> Int -> Int
value []   pos neg | neg > 0   = -(pos+1)
		   | otherwise = -(neg)+1
value (x:xs) pos neg | x == 0  	 = 0
      	     	     | x > 0  	 = if (x > pos)
		       	      	   then value xs x neg
				   else value xs pos neg
		     | otherwise = if (x > neg)
		       		   then value xs pos x
				   else value xs pos neg