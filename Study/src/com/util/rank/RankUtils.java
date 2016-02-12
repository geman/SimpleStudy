package com.util.rank;

import java.util.ArrayList;
import java.util.List;

/**
 * 排列 帮助类
 * @author gecy 
 *
 * 2016年2月12日
 */
public class RankUtils {
	
	/**
	 * 排列
	 * @param sources 源数据集合
	 * @param num 被排列的数目
	 * @return 排列结果
	 */
	public static <T> List<List<T>> findRank(List<T> sources,int num){
		List<List<T>> groupList = findGroup(sources, num) ;
		List<List<T>> resultList = new ArrayList<>() ;
		for (List<T> list : groupList) {
			List<List<T>> chrildrenRsList = findAllRank(list) ;
			resultList.addAll(chrildrenRsList) ;
		}
		
		return resultList ;
	}
	
	/**
	 * 组合
	 * @param sources 源数据集合
	 * @param num 被组合的数目
	 * @return 组合结果
	 */
	public static <T> List<List<T>> findGroup(List<T> sources,int num){
		List<List<T>> resultList = new ArrayList<>() ;
		List<List<Integer>> indexesList = lookupGroupIndexes(sources.size(),num);
		for (int i = 0; i < indexesList.size(); i++) {
			List<T> rses = new ArrayList<>() ;
			List<Integer> indexList = indexesList.get(i) ;
			for (int j = 0; j < indexList.size(); j++) {
				rses.add(sources.get(indexList.get(j))) ;
			}
			resultList.add(rses) ;
		}
		return resultList ;
	}
	/**
	 * 全排列
	 * @param sources 源数据集合
	 * @return 排列结果
	 */
	public static <T> List<List<T>> findAllRank(List<T> sources){
		
		List<List<T>> resultList = new ArrayList<>() ;
		List<List<Integer>> indexesList = lookupAllRankIndexes(sources.size()) ; 
		
		for (int i = 0; i < indexesList.size(); i++) {
			List<Integer> indexList = indexesList.get(i) ;
			List<T> rankList = new ArrayList<>() ;
			List<T> copySources =  new ArrayList<>() ;
			copySources.addAll(sources) ;
			for (int j = 0; j < indexList.size(); j++) {
				int index = indexList.get(j) ;
				rankList.add(copySources.remove(index)) ;
			}
			resultList.add(rankList) ;
		}
		
		return resultList ;
	}
	//寻找组合结果（下标）
	private static List<List<Integer>> lookupGroupIndexes(int size,int pickNum){
		List<List<Integer>> indexesList = new ArrayList<>() ;
		circleLookupGroupIndex(indexesList,size,pickNum,new ArrayList<>()) ;
		
		return indexesList ;
	}
	//寻找组合结果（下标） - 递归
	private static void circleLookupGroupIndex(List<List<Integer>> indexesList, int size, int pickNum,
			List<Integer> indexList) {
		if(pickNum == indexList.size()){
			List<Integer> copyIndex = new ArrayList<>() ;
			copyIndex.addAll(indexList) ;
			indexesList.add(copyIndex) ;
			return ;
		}
		int lastIndex = indexList.size() == 0 ? 0 : indexList.get(indexList.size()-1)+1 ; 
		for (int i = lastIndex ; i < size; i++) {
			indexList.add(i) ;
			circleLookupGroupIndex(indexesList,size,pickNum,indexList);
			indexList.remove(indexList.size()-1) ;
		}
	}

	//寻找排列结果 (下标)
	private static List<List<Integer>> lookupAllRankIndexes(int size) {
		List<List<Integer>> indexesList = new ArrayList<>() ;
		circleLookupAllRankIndex(indexesList,size,new ArrayList<>()) ;
		
		return indexesList;
	}
	//寻找排列结果 (下标) -递归
	private static void circleLookupAllRankIndex(List<List<Integer>> indexesList, int size, List<Integer> indexList) {
		int indexSize = indexList.size() ;
		if(size ==  indexSize){ //排列index完成
			List<Integer> copyIndex = new ArrayList<>() ;
			copyIndex.addAll(indexList) ;
			indexesList.add(copyIndex) ;
			return ;
		}
		
		int range = size - indexList.size() ;
		for (int i = 0; i < range; i++) {
			indexList.add(i) ; //添加
			circleLookupAllRankIndex(indexesList,size,indexList) ; // 将产生一个排序结果
			indexList.remove(indexList.size()-1) ; //移除，让下个循环继续使用
		}
	}
}
