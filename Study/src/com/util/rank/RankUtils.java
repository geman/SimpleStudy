package com.util.rank;

import java.util.ArrayList;
import java.util.List;

/**
 * ���� ������
 * @author gecy 
 *
 * 2016��2��12��
 */
public class RankUtils {
	
	/**
	 * ����
	 * @param sources Դ���ݼ���
	 * @param num �����е���Ŀ
	 * @return ���н��
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
	 * ���
	 * @param sources Դ���ݼ���
	 * @param num ����ϵ���Ŀ
	 * @return ��Ͻ��
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
	 * ȫ����
	 * @param sources Դ���ݼ���
	 * @return ���н��
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
	//Ѱ����Ͻ�����±꣩
	private static List<List<Integer>> lookupGroupIndexes(int size,int pickNum){
		List<List<Integer>> indexesList = new ArrayList<>() ;
		circleLookupGroupIndex(indexesList,size,pickNum,new ArrayList<>()) ;
		
		return indexesList ;
	}
	//Ѱ����Ͻ�����±꣩ - �ݹ�
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

	//Ѱ�����н�� (�±�)
	private static List<List<Integer>> lookupAllRankIndexes(int size) {
		List<List<Integer>> indexesList = new ArrayList<>() ;
		circleLookupAllRankIndex(indexesList,size,new ArrayList<>()) ;
		
		return indexesList;
	}
	//Ѱ�����н�� (�±�) -�ݹ�
	private static void circleLookupAllRankIndex(List<List<Integer>> indexesList, int size, List<Integer> indexList) {
		int indexSize = indexList.size() ;
		if(size ==  indexSize){ //����index���
			List<Integer> copyIndex = new ArrayList<>() ;
			copyIndex.addAll(indexList) ;
			indexesList.add(copyIndex) ;
			return ;
		}
		
		int range = size - indexList.size() ;
		for (int i = 0; i < range; i++) {
			indexList.add(i) ; //���
			circleLookupAllRankIndex(indexesList,size,indexList) ; // ������һ��������
			indexList.remove(indexList.size()-1) ; //�Ƴ������¸�ѭ������ʹ��
		}
	}
}
