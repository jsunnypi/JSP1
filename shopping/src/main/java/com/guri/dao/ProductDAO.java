package com.guri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.guri.dto.ProductVO;

import util.DBManager;

public class ProductDAO {

	private ProductDAO() {

	}

	private static ProductDAO instance = new ProductDAO();

	public static ProductDAO getinstance() {
		return instance;
	}

	// 최근 상품 출력하는 메서드

	public List<ProductVO> selectAllProducts() {
		String sql = "select * from product order by code desc";
		List<ProductVO> list = new ArrayList<ProductVO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductVO pvo = new ProductVO();
				pvo.setCode(rs.getInt("code"));
				pvo.setName(rs.getString("name"));
				pvo.setPrice(rs.getInt("price"));
				pvo.setPictureUrl(rs.getString("pictureUrl"));
				pvo.setDescription(rs.getString("description"));

				list.add(pvo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;

	}

	public ProductVO selectProductBycode(String code) {
		String sql = "select * from product where code=?";
		ProductVO pvo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pvo = new ProductVO();
				pvo.setCode(rs.getInt("code"));
				pvo.setName(rs.getString("name"));
				pvo.setPrice(rs.getInt("price"));
				pvo.setPictureUrl(rs.getString("pictureUrl"));
				pvo.setDescription(rs.getString("description"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return pvo;
	}

	// 상품 등록 메서드
	public void insertProduct(ProductVO pvo) {
		String sql = "insert into product values(product_seq.nextval, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pvo.getName());
			pstmt.setInt(2, pvo.getPrice());
			pstmt.setString(3, pvo.getPictureUrl());
			pstmt.setString(4, pvo.getDescription());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

	}

	// 상품 수정 메서드
	public void updateProduct(ProductVO pvo) {
		String sql = "update product set values(?, ?, ?, ?) where code=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pvo.getName());
			pstmt.setInt(2, pvo.getPrice());
			pstmt.setString(3, pvo.getPictureUrl());
			pstmt.setString(4, pvo.getDescription());
			pstmt.setInt(5, pvo.getCode());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

	}

}
