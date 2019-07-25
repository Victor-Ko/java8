function splArr(arr)
{
  try
  {
    var outArr = [];
    
    for(var iLoop =0 ; iLoop < arr.length; iLoop++)
    {
       Array.isArray( arr[iLoop] ) ? outArr = outArr.concat( splArr( arr[iLoop] ) ) : outArr.push( arr[iLoop] );
      /*
       if( Array.isArray( arr[iLoop] ) ){
         outArr = outArr.concat( splArr( arr[iLoop] ) );
       }else{
          outArr.push( arr[iLoop] ); 
       }
       */
    }
    return outArr.sort().join(',');
  }catch(ex){
    console.error("splArr - " +ex.message);
    return '';
  }
  
}

console.log( splArr([ 'a', ['b', 'c', 'd',['1','2']], 'e', ['f','g'],'h']));