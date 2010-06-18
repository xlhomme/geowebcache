/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Arne Kepp / The Open Planning Project 2009
 *  
 */
package org.geowebcache.storage;

/**
 * Manages the persistence of the actual data contained in cacheable objects (tiles, WFS responses).
 * <p>
 * Blobstores may assume that the StorageObjects passed to them are completely filled in except for
 * the blob fields.
 * </p>
 */
public interface BlobStore {
    /**
     * Delete a blob from storage. Does not modify the passed object
     * 
     * @param key
     * @return true if succeeded, false if key did not exist
     */
    public boolean delete(String layerName) throws StorageException;
    public boolean delete(TileObject obj) throws StorageException;
    public boolean delete(WFSObject obj) throws StorageException;
    public boolean delete(TileRange obj) throws StorageException;
    
    /**
     * Retrieve a blob from storage. Calls setBlob() on passed object.
     * 
     * @param key
     * @return data, byte[0] if the blob is empty, null if it did not exist
     * @throws StorageException
     */
    public byte[] get(TileObject obj) throws StorageException;
    public long get(WFSObject obj) throws StorageException;
    
    /**
     * Store blob. Calls getBlob() on passed object, does not modify the object.
     * 
     * @param key
     * @param blog
     * @throws StorageException
     */
    public void put(TileObject obj) throws StorageException;
    public void put(WFSObject obj) throws StorageException;

    /**
     * Wipes the entire storage. Should only be invoked during testing.
     * 
     * @throws StorageException
     */
    public void clear() throws StorageException;
    
    /** 
     * Destroy method for Spring
     */
    public void destroy();
    
    /**
     * Calculates the cache size in MB for the given layer
     * <p>
     * This method is potentially (and most probably) resource intensive, and thread safe; so feel
     * free to call it from a worker/background/separate thread so the it does not blocks normal
     * program flow.
     * </p>
     * <p>
     * Also, this method does not guarantee to return an exact result if the cache is modified by
     * another thread while it is running, so beware.
     * </p>
     * 
     * @param layerName
     * @return
     */
    public double calculateCacheSize(String layerName) throws StorageException;
    
    ///** 
    // * Test to see whether the blobstore is ready or not
    // */
    //public boolean isReady();
}
