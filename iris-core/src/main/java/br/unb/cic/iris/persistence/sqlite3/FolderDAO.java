package br.unb.cic.iris.persistence.sqlite3;

import static br.unb.cic.iris.i18n.Message.message;

import java.util.List;

import br.unb.cic.iris.core.exception.DBException;
import br.unb.cic.iris.core.model.IrisFolder;
import br.unb.cic.iris.persistence.IFolderDAO;

public class FolderDAO extends AbstractDAO<IrisFolder> implements IFolderDAO {
	private static final String FIND_BY_NAME = "FROM IrisFolder f " + "where f.name = :pName";

	public IrisFolder findByName(String folderName) throws DBException {
		try {
			startSession();
			List<IrisFolder> entries = session.createQuery(FIND_BY_NAME).setParameter("pName", folderName).list();

			if (entries != null && entries.size() == 1) {
				return entries.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new DBException(message("error.folder.not.found", folderName), e);
		} finally {
			closeSession();
		}
	}
}
