{-
  chocolat.hs
  Authors: Honoré Nintunze & Antoine Petit
-}

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

{-value:: [Int] -> Int -> Int -> Int
value[] pos neg | neg > 0   = -(pos+1)
                  | otherwise = -(neg)+1
value (x:xs) pos neg | x == 0    = 0
                      | x > 0     = if (x > pos)
                                    then value' xs x neg
                                    else value' xs pos neg
                      | otherwise = if (x > neg)
                                    then value' xs pos x
                                    else value' xs pos neg
-}
value::([Int],[Int]) -> Int
value ([],[]) = error "The lists mustn't be empty"
value (xs,[]) = -((maximum xs)+1)
value (_,xs)  = -(maximum xs)+1


f::Chocolate -> Int
f (1,1,0,0) = 0
f (m,n,i,j) = value ((fst r) ++ (fst s),(snd r) ++ (snd s))
    where g k (xs,ys) | k < m = if k <= i
                                then
                                  let a = f (m-k,n,i-k,j)
                                  in case a of  0         -> (xs,(a:ys))
                                                otherwise ->  if a > 0
                                                              then
                                                                g (k+1) ((a:xs),ys)
                                                              else
                                                                g (k+1) (xs,(a:ys))
                                else
                                  let b = f (k,n,i,j)
                                  in case b of  0         -> (xs,(b:ys))
                                                otherwise ->  if b > 0
                                                              then
                                                                g (k+1) ((b:xs),ys)
                                                              else
                                                                g (k+1) (xs,(b:ys))
                      | otherwise = (xs,ys)
                        
          h k (xs,ys) | k < n = if k <= j
                                then
                                  let c = f (m,n-k,i,j-k)
                                  in case c of  0         -> (xs,(c:ys))
                                                otherwise ->  if c > 0
                                                              then
                                                                h (k+1) ((c:xs),ys)
                                                              else
                                                                h (k+1) (xs,(c:ys))
                                else
                                  let d = f (m,k,i,j)
                                  in case d of  0         -> (xs,(d:ys))
                                                otherwise ->  if d > 0
                                                              then
                                                                h (k+1) ((d:xs),ys)
                                                              else
                                                                h (k+1) (xs,(d:ys))
                      | otherwise = (xs,ys)
          r = g 1 ([],[])
          s = h 1 ([],[])