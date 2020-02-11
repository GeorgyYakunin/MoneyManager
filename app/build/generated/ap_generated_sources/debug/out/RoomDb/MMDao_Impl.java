package RoomDb;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MMDao_Impl implements MMDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Transaction> __insertionAdapterOfTransaction;

  private final EntityInsertionAdapter<Category> __insertionAdapterOfCategory;

  private final EntityDeletionOrUpdateAdapter<Transaction> __deletionAdapterOfTransaction;

  private final EntityDeletionOrUpdateAdapter<Category> __deletionAdapterOfCategory;

  private final EntityDeletionOrUpdateAdapter<Transaction> __updateAdapterOfTransaction;

  private final EntityDeletionOrUpdateAdapter<Category> __updateAdapterOfCategory;

  public MMDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransaction = new EntityInsertionAdapter<Transaction>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Transactions` (`Id`,`Name`,`Amount`,`date`,`IsIncome`,`CategoryId`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Transaction value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindDouble(3, value.getAmount());
        final Long _tmp;
        _tmp = Converters.dateToTimestamp(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp);
        }
        final Integer _tmp_1;
        _tmp_1 = value.getIsIncome() == null ? null : (value.getIsIncome() ? 1 : 0);
        if (_tmp_1 == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, _tmp_1);
        }
        stmt.bindLong(6, value.getCategoryId());
      }
    };
    this.__insertionAdapterOfCategory = new EntityInsertionAdapter<Category>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Categories` (`Id`,`Name`,`IsIncome`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Category value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        final Integer _tmp;
        _tmp = value.getIsIncome() == null ? null : (value.getIsIncome() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
      }
    };
    this.__deletionAdapterOfTransaction = new EntityDeletionOrUpdateAdapter<Transaction>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Transactions` WHERE `Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Transaction value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__deletionAdapterOfCategory = new EntityDeletionOrUpdateAdapter<Category>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Categories` WHERE `Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Category value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfTransaction = new EntityDeletionOrUpdateAdapter<Transaction>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Transactions` SET `Id` = ?,`Name` = ?,`Amount` = ?,`date` = ?,`IsIncome` = ?,`CategoryId` = ? WHERE `Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Transaction value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindDouble(3, value.getAmount());
        final Long _tmp;
        _tmp = Converters.dateToTimestamp(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp);
        }
        final Integer _tmp_1;
        _tmp_1 = value.getIsIncome() == null ? null : (value.getIsIncome() ? 1 : 0);
        if (_tmp_1 == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, _tmp_1);
        }
        stmt.bindLong(6, value.getCategoryId());
        stmt.bindLong(7, value.getId());
      }
    };
    this.__updateAdapterOfCategory = new EntityDeletionOrUpdateAdapter<Category>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Categories` SET `Id` = ?,`Name` = ?,`IsIncome` = ? WHERE `Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Category value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        final Integer _tmp;
        _tmp = value.getIsIncome() == null ? null : (value.getIsIncome() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        stmt.bindLong(4, value.getId());
      }
    };
  }

  @Override
  public void AddTransaction(final Transaction transaction) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTransaction.insert(transaction);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void AddCategory(final Category category) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCategory.insert(category);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void DeleteTransaction(final Transaction transaction) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTransaction.handle(transaction);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void DeleteCategory(final Category category) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCategory.handle(category);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void UpdateTransaction(final Transaction transaction) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTransaction.handle(transaction);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void UpdateCategory(final Category category) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCategory.handle(category);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Transaction> GetTransaction() {
    final String _sql = "SELECT * FROM `Transactions`";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "Amount");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfIsIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "IsIncome");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "CategoryId");
      final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Transaction _item;
        _item = new Transaction();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        _item.setAmount(_tmpAmount);
        final Date _tmpDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfDate);
        }
        _tmpDate = Converters.fromTimestamp(_tmp);
        _item.setDate(_tmpDate);
        final Boolean _tmpIsIncome;
        final Integer _tmp_1;
        if (_cursor.isNull(_cursorIndexOfIsIncome)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getInt(_cursorIndexOfIsIncome);
        }
        _tmpIsIncome = _tmp_1 == null ? null : _tmp_1 != 0;
        _item.setIsIncome(_tmpIsIncome);
        final long _tmpCategoryId;
        _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
        _item.setCategoryId(_tmpCategoryId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Transaction> GetTransactionofType(final Boolean isIncome) {
    final String _sql = "SELECT * FROM `Transactions` WHERE IsIncome = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final Integer _tmp;
    _tmp = isIncome == null ? null : (isIncome ? 1 : 0);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "Amount");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfIsIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "IsIncome");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "CategoryId");
      final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Transaction _item;
        _item = new Transaction();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        _item.setAmount(_tmpAmount);
        final Date _tmpDate;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfDate);
        }
        _tmpDate = Converters.fromTimestamp(_tmp_1);
        _item.setDate(_tmpDate);
        final Boolean _tmpIsIncome;
        final Integer _tmp_2;
        if (_cursor.isNull(_cursorIndexOfIsIncome)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getInt(_cursorIndexOfIsIncome);
        }
        _tmpIsIncome = _tmp_2 == null ? null : _tmp_2 != 0;
        _item.setIsIncome(_tmpIsIncome);
        final long _tmpCategoryId;
        _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
        _item.setCategoryId(_tmpCategoryId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Transaction GetTransactionById(final Long id) {
    final String _sql = "SELECT * FROM `Transactions` WHERE Id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "Amount");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfIsIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "IsIncome");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "CategoryId");
      final Transaction _result;
      if(_cursor.moveToFirst()) {
        _result = new Transaction();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        _result.setAmount(_tmpAmount);
        final Date _tmpDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfDate);
        }
        _tmpDate = Converters.fromTimestamp(_tmp);
        _result.setDate(_tmpDate);
        final Boolean _tmpIsIncome;
        final Integer _tmp_1;
        if (_cursor.isNull(_cursorIndexOfIsIncome)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getInt(_cursorIndexOfIsIncome);
        }
        _tmpIsIncome = _tmp_1 == null ? null : _tmp_1 != 0;
        _result.setIsIncome(_tmpIsIncome);
        final long _tmpCategoryId;
        _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
        _result.setCategoryId(_tmpCategoryId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Transaction> GetTransactionsOfaDate(final Date date) {
    final String _sql = "SELECT * FROM `Transactions` WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final Long _tmp;
    _tmp = Converters.dateToTimestamp(date);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "Amount");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfIsIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "IsIncome");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "CategoryId");
      final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Transaction _item;
        _item = new Transaction();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        _item.setAmount(_tmpAmount);
        final Date _tmpDate;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfDate);
        }
        _tmpDate = Converters.fromTimestamp(_tmp_1);
        _item.setDate(_tmpDate);
        final Boolean _tmpIsIncome;
        final Integer _tmp_2;
        if (_cursor.isNull(_cursorIndexOfIsIncome)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getInt(_cursorIndexOfIsIncome);
        }
        _tmpIsIncome = _tmp_2 == null ? null : _tmp_2 != 0;
        _item.setIsIncome(_tmpIsIncome);
        final long _tmpCategoryId;
        _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
        _item.setCategoryId(_tmpCategoryId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Category> GetCategories() {
    final String _sql = "SELECT * FROM `Categories`";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
      final int _cursorIndexOfIsIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "IsIncome");
      final List<Category> _result = new ArrayList<Category>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Category _item;
        _item = new Category();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final Boolean _tmpIsIncome;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfIsIncome)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfIsIncome);
        }
        _tmpIsIncome = _tmp == null ? null : _tmp != 0;
        _item.setIsIncome(_tmpIsIncome);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Category> GetCategoriesofType(final Boolean isIncome) {
    final String _sql = "SELECT * FROM `Categories` WHERE  IsIncome = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final Integer _tmp;
    _tmp = isIncome == null ? null : (isIncome ? 1 : 0);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
      final int _cursorIndexOfIsIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "IsIncome");
      final List<Category> _result = new ArrayList<Category>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Category _item;
        _item = new Category();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final Boolean _tmpIsIncome;
        final Integer _tmp_1;
        if (_cursor.isNull(_cursorIndexOfIsIncome)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getInt(_cursorIndexOfIsIncome);
        }
        _tmpIsIncome = _tmp_1 == null ? null : _tmp_1 != 0;
        _item.setIsIncome(_tmpIsIncome);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Category GetCategoryById(final Long id) {
    final String _sql = "SELECT * FROM `Categories` WHERE Id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
      final int _cursorIndexOfIsIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "IsIncome");
      final Category _result;
      if(_cursor.moveToFirst()) {
        _result = new Category();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
        final Boolean _tmpIsIncome;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfIsIncome)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfIsIncome);
        }
        _tmpIsIncome = _tmp == null ? null : _tmp != 0;
        _result.setIsIncome(_tmpIsIncome);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
